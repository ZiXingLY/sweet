// miniprogram/pages/home/home.js
const wxParse = getApp().globalData.wxParse;
const timestampToTime = getApp().globalData.timeToTime.timestampToTime;
const ROOT_URL = getApp().globalData.netUtil.ROOT_URL;
const IMG_URL = getApp().globalData.netUtil.IMG_URL;
const _api = getApp().globalData.api;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    emotionIndex: 1,
    articleIndex: 1,
    pullDir: 0,
    imgs: [],
    category: [],
    selectSlide: 10,
    emotions: [],
    isInfinite: false,
    walls: [],
    tiebas: [],
    dataList: [],
    momentPage: 0,
    tiebaPage: 0,
    wallPage:0,
    tiebaTotal: 0
  },
// 点击轮播图
  goToDetail(e){
    let id = e.currentTarget.dataset.idx;
    console.log(e.currentTarget.dataset.idx)
    wx.navigateTo({
      url: '../emotionDetail/emotionDetail?id='+id,
    })
  },
  goToQemotionDetail(e){
    let tid = e.currentTarget.dataset.idx;
    let type = e.currentTarget.dataset.type;
    
    wx.navigateTo({
      url: '../qEmotionDetail/qEmotionDetail?tid=' + tid+'&type=' + type,
    })
  },
// 初始化nav
  initCategory: function(){
    // const db = wx.cloud.database();
    // db.collection('category')
    // .get()
    // .then(res => {
    //   this.setData({
    //     category: res.data
    //   })
    // })
    let _this = this;
    wx.request({
      url: ROOT_URL + '/category/getCategory',
      header: {
        'content-type': 'application/json', // 默认值
        'x-auth-token': wx.getStorageSync('token')
      },
      success(res){
        console.log(res);
        _this.setData({
        category: res.data
      })
      },
      fail(err){
        console.log(err);
      }
    })
  },
  // initRecordNo: function(){
  //   const db = wx.cloud.database()
  //   db.collection('emotions')
  //   // .where({
  //   //   _openid: 'xxx' // 填入当前用户 openid
  //   // })
  //   .count().then(res => {
  //     console.log(res.total)
  //   })

  // },
  initSlideShow: function(){
    const db = wx.cloud.database()
    db.collection('slideshow')
      .where({ type: 1 })
      .get()
      .then(res => {
        this.setData({
          imgs: res.data
        })
      })
  },
  changeType: function(e){

    this.setData({
      selectSlide: e.currentTarget.dataset.index
    })
  },
  getWalls: function(page){

    let _this = this;

    wx.request({
      url: ROOT_URL +'/sys/sweet/wall/list?page='+page,
      header: {
        'content-type': 'application/json', // 默认值
        'x-auth-token': wx.getStorageSync('token')
      },
      success(res) {

        res.data.data.list.map(item => {
          // item.createTime = timestampToTime(item.createTime.$date);
          item.content = wxParse.strDiscode(item.content);
          item.authorImg = '../../images/user.gif';
          item.authorName = '表白墙';
          item.info = JSON.parse(item.info)
          console.log(item)
          return item;
        })

        let conver = res.data.data.list.map(item => {
          item.author = wxParse.strDiscode(item.info.name);

          if (item.info.commentlist) {
            console.log('format commentList!')
            item.info.commentlist.map(record => {
              record.content = wxParse.strDiscode(record.content);
              record.name = wxParse.strDiscode(record.name);
              return record
            })
          }
          return item;
        }
        )
        _this.setData({
          walls: res.data.data.list,
          wallPage: ++_this.data.wallPage
        })
        console.log(res.data.data.list[0].info) 
      }
    })

  },

  getTiebas(page){

    let _this = this;

    wx.request({
      url: ROOT_URL+'/sys/sweet/tieba/list?page='+page,
      header: {
        'content-type': 'application/json', // 默认值
        'x-auth-token': wx.getStorageSync('token')
      },
      success(res) {

        res.data.data.list.map(item => {
          // item.createTime = timestampToTime(item.createTime.$date);
          item.content = wxParse.strDiscode(item.content);
          item.authorImg = '../../images/user.gif';
          item.authorName = '贴吧君';
          item.info = JSON.parse(item.info)
          return item;
        })

        let conver = res.data.data.list.map(item => {
          item.author = wxParse.strDiscode(item.info.name);

          if (item.info.commentlist){
            console.log('format commentList!')
            item.info.commentlist.map(record => {
              record.name = wxParse.strDiscode(record.name)
              record.content = wxParse.strDiscode(record.content)
              return record
            })
          }
          return item;
        }
        )

        _this.setData({
          tiebas: res.data.data.list,
          tiebaPage: ++_this.data.tiebaPage,
          tiebaTotal: res.data.data.total
        })

        console.log(res.data.data)
      }
    })

  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // 初始化轮播图
    this.initSlideShow()
    // 初始化NAV标签
    this.initCategory()
    // 加载emotions
    // this.initRecordNo()
    this.getEmotions()
    this.getArticles()

    this.getWalls(this.data.wallPage)
    this.getTiebas(this.data.tiebaPage)


  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.getEmotions()
    console.log(this.route+"reshow")
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    this.getEmotions();
    this.getArticles();
  },

  gotoMoment: function(e){
    let id = e.currentTarget.dataset.idx;
    console.log(e.currentTarget.dataset.idx)
    wx.navigateTo({
      url: '../momentDetail/momentDetail?id=' + id,
    })
  },

  getMoreEmotions(){
    _api.getEmotions({ pageNum: this.data.emotionIndex}, (res) => {
      console.log(res);
      if (res.code == 0) {
        let list = res.data.list.map(item => {
          item.createTime = timestampToTime(item.add_time);
          item.content = wxParse.strDiscode(item.content);
          item.authorImg = '../../images/user.gif';
          item.authorName = '安石';
          return item;
        })
        let page = this.data.emotionIndex
        if (res.data.pages > page){
          page++
        }
        this.setData({
          emotions: this.data.emotions.concat(list),
          emotionIndex: page
        })
      }
    })
  },

  getMoreArticles(){

    let _this = this;

    console.log(wx.getStorageSync('token'));
    wx.request({
      url: ROOT_URL + '/article/list',
      header: {
        'content-type': 'application/json', // 默认值
        'x-auth-token': wx.getStorageSync('token')
      },
      data: {
        pageNum: this.data.articleIndex
      },
      success(res) {
        console.log(res);
        if (res.data.code != 0){
          return;
        }
        if (res.data.data.list.length > 0) {
          let list = res.data.data.list.map(item => {
            item.coverimg = IMG_URL + item.coverimg;
            return item
          })
          let page = res.data.data.pages;
          if (_this.data.articleIndex < page){
            page++
          }
          _this.setData({
            dataList: _this.data.dataList.concat(list),
            articleIndex: page
          })
        }else{
          this.setData({
            isInfinite: true
          })
        }
      }
    })

    // wx.cloud.callFunction({
    //   name: 'readEmotions',
    //   data: {
    //     start: this.data.emotionIndex
    //   }
    // }).then(res => {
      // // console.log(res.result.data)
      // let size = 0;
      // let list = res.result.data.map(item => {
      //   item.createTime = timestampToTime(item.createTime.$date);
      //   item.content = wxParse.strDiscode(item.content);
      //   item.authorImg = '../../images/user.gif';
      //   item.authorName = '安石';
      //   size++;
      //   return item;
      // });

      // if (size == 0) {
      //   this.setData({
    //       isInfinite: true
    //     })
    //   }
    //   // let isInfinite;
    //   console.log(list)
    //   this.setData({
    //     emotions: this.data.emotions.concat(list),
    //     emotionIndex: this.data.emotionIndex + size
    //   })
    //   wx.hideNavigationBarLoading();
    // })

  },

  getMoreTieba(){
    let _this = this;
    wx.request({
      url: ROOT_URL + '/sys/sweet/tieba/list?page=' + this.data.tiebaPage,
      header: {
        'content-type': 'application/json', // 默认值
        'x-auth-token': wx.getStorageSync('token')
      },
      success(res) {
        res.data.data.list.map(item => {
          item.content = wxParse.strDiscode(item.content);
          item.authorImg = '../../images/user.gif';
          item.authorName = '贴吧君';
          item.info = JSON.parse(item.info)
          return item;
        })
        let conver = res.data.data.list.map(item => {
          item.author = wxParse.strDiscode(item.info.name);
          if (item.info.commentlist){
            item.info.commentlist.map(record => {
              record.content = wxParse.strDiscode(record.content)
              record.name = wxParse.strDiscode(record.name)
              return record
            })
          }
          return item;
        })
        _this.setData({
          tiebas: _this.data.tiebas.concat(res.data.data.list),
          tiebaPage: ++_this.data.tiebaPage
        })
      }
    })
  },

  getMoreWall(){
    let _this = this;
    wx.request({
      url: ROOT_URL + '/sys/sweet/wall/list?page=' + this.data.wallPage,
      header: {
        'content-type': 'application/json', // 默认值
        'x-auth-token': wx.getStorageSync('token')
      },
      success(res) {
        console.log(res.data.data.list.length)
        res.data.data.list.map(item => {
          // item.createTime = timestampToTime(item.createTime.$date);
          item.content = wxParse.strDiscode(item.content);
          item.authorImg = '../../images/user.gif';
          item.authorName = '表白墙';
          item.info = JSON.parse(item.info)
          return item;
        })
        let conver = res.data.data.list.map( item =>
          {
          item.author = wxParse.strDiscode(item.info.name);

          if (item.info.commentlist){
            item.info.commentlist.map(record => {
              record.content = wxParse.strDiscode(record.content)
              return record
            })
          }
          return item;
          }
        )
        _this.setData({
          walls: _this.data.walls.concat(res.data.data.list),
          wallPage: ++_this.data.wallPage
        })
      }
    })
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

    if(this.data.selectSlide == 10){
      this.getMoreArticles()
    }
    if (this.data.selectSlide == 11){
      this.getMoreEmotions()
    }
    if (this.data.selectSlide == 12) {
      this.getMoreWall()
    }
    if (this.data.selectSlide == 13) {
      this.getMoreTieba()
    }

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  getMoreMoment(){

  },

  // 获取动态
  getEmotions(){
    _api.getEmotions({}, (res) => {
      console.log(res);
      if (res.code == 0) {
        let list = res.data.list.map(item => {
          item.createTime = timestampToTime(item.add_time);
         item.content = wxParse.strDiscode(item.content);
         item.authorImg = '../../images/user.gif';
         item.authorName = '安石';
         return item;
        })
        let page = this.data.emotionIndex
        if (res.data.pages > page) {
          page++
        }
        this.setData({
          emotions: list,
          emotionIndex: page
        })
      }
    })
    // const db = wx.cloud.database()
    // db.collection('moments')
    // .orderBy('createTime', 'desc')
    // .get()
    // .then(res => {
    //   console.log(res)

    //   let list = res.data.map(item => {
    //     item.createTime = timestampToTime(item.createTime);
        // item.content = wxParse.strDiscode(item.content);
    //     item.authorImg = '../../images/user.gif';
    //     item.authorName = '安石';
    //     return item;
    //   })

    //   console.log(list)

    //   console.log(list[0]['paths'])

    //   this.setData({
    //     moments: list
    //   })
    // })
  },
  getArticles() {
    let _this = this;
    wx.request({
      url: ROOT_URL + 'article/list',
      header: {
        'content-type': 'application/json', // 默认值
        'x-auth-token': wx.getStorageSync('token')
      },
      success(res) {
        console.log(res);
        if(res.data.code != 0){
          return;
        }
        if (res.data.data.list.length > 0){
          let list = res.data.data.list.map(item => {
            item.coverimg = IMG_URL + item.coverimg;
            return item
          })
          let page = res.data.data.pages;
          if (_this.data.articleIndex < page) {
            page++
          }
          _this.setData({
            dataList: list,
            articleIndex: page
          })
        }
      }
    })
    // wx.cloud.callFunction({
    //   name: 'readEmotions',
    //   data: {
    //     start: 0
    //   }
    // }).then(res => {
    //   console.log(res.result.data)

    //   // if (res.result.data.length == 0){
    //   //   this.setData({

    //   //   })
    //   // }
    //   let list = res.result.data.map(item => {
    //     item.createTime = timestampToTime(item.createTime.$date);
    //     item.content = wxParse.strDiscode(item.content);
    //     item.authorImg = '../../images/user.gif';
    //     item.authorName = '安石';
    //     return item;
    //   });
    //   // let isInfinite;
    //   // console.log(list)
    //   this.setData({
    //     emotions: list,
    //     emotionIndex: list.length
    //   })
    //   wx.hideNavigationBarLoading();
    // })
  },
  goToArticleDetail(e){
    wx.navigateTo({
      url: '../article-detail/article-detail?id=' + e.currentTarget.dataset.item.aid,
    })
  }
})