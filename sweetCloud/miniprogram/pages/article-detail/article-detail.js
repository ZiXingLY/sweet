// pages/article-detail/article-detail.js
let cookie, _openid;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    detailData: {},
    userData: {},
    id: '',
    shareNumber: 0,
    likenum: 0,
    isZan: false,
    imgUrl: getApp().globalData.IMG_URL
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    wx.setNavigationBarTitle({
      title: '文章详情',
    });
    cookie = wx.getStorageSync("cookie"); 
    _openid = wx.getStorageSync("openid");   
    this.getDetail({ id: options.id});
    getApp().globalData.api.shareNum({ id: options.id ,type:1}, (res) => {
      console.log(res);
      this.setData({
        shareNumber: res['data']['shareNumber']
      })
    })
    let params = {
      openid: _openid
    }
    // // 获取用户信息
    // getApp().globalData.api.getUserInfo(params, (res) => {
    //   console.log(res);
    //   if (typeof res['data'] == 'object') {
    //     this.setData({
    //       userInfo: res['data']
    //     })
    //   }
    // })
    // 检查是否点过赞
    getApp().globalData.api.checkZan({ "cookie": cookie }, { aid: options.id }, (res) => {
      console.log(res);
      if (res['data']['data'] == "liked"){
        this.setData({
          isZan: true
        })
      }
    });
    // 如果是分享进入
    if (options.type == "share"){
      this.getDetail({ id: options.aid });
      getApp().globalData.api.addIntegral({ aid: options.aid, openid: options.openid, addByOpenid: _openid}, (res) => {
        console.log(res);
      })
    }
  },
  getDetail(params){
    getApp().globalData.api.articleDetail({"cookie": cookie}, params, (res) => {
      console.log(res);
      res.article.addtime = this.timestampToTime(res.article.addtime);
      res.article.content = res.article.content.replace(/\<img/gi, '<img class="rich-img" ');
      res.article.content = getApp().globalData.wxParse.strDiscode(res.article.content);
      this.setData({
        detailData: res.article,
        userData: res.user,
        id: params.id,
        likenum: res.article.likenum
      })
    })
  },
  // 时间戳转换时间
  timestampToTime(timestamp) {
    let date = new Date(timestamp);
    let Y = date.getFullYear() + '-';
    let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    let D = date.getDate();
    return Y + M + D;
  },
  // 点赞
  thumbs(){
    console.log('点赞');
    if (!this.data.isZan){
      let params = {
        id: this.data.id
      };
      let header = {
        "cookie": cookie
      };
      getApp().globalData.api.goodThumbs(header, params, (res) => {
        console.log(res);
        if (typeof res['data'] == 'object') {
          // 已绑定手机号
          let likenum = this.data.likenum;
          likenum++;
          this.setData({
            likenum: likenum,
            isZan: true
          })
        } else {
          // 未绑定手机号
          wx.showToast({
            title: '请先绑定手机号',
            icon: 'none'
          });
        }
      })
    }
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
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage () {
    return {
      title: this.data.detailData.title,
      path: '/pages/article-detail/article-detail?type=share&openid=' + _openid + "&aid=" + this.data.detailData.aid
    }
  }
})