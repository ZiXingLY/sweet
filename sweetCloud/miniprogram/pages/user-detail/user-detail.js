// pages/photographer/photographer.js
const app = getApp();
// var url = app.globalData.servsers;
// var imgUrl = app.globalData.imgUrl;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    // cui 简介下标
    ids: [],
    swiperIndex: 0,
    page: 1,
    // imgUrl: imgUrl,//图片拼接地址
    author: {},//作者详情数据
    putBull: false,//判断是否可以进行上拉加载
    total: "",//作品总数
    // 作品列表
    goodsArr: [],
    // 高度
    swiper_height: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let mydata = wx.getStorageSync("token");
    this.setData({
      id: options.id,
      mydata: mydata
    })
  },

  /**
   * 获取商品的id
   */
  _getDetail: function (e) {
    let ids = JSON.stringify(this.data.ids);
    wx.navigateTo({
      url: '../goods-detail/goods-detail?id=' + e.detail.currentTarget.dataset['id'] + "&ids=" + ids,
    })
  },
  /**
   * 跳转换购作品
   */
  go_buy_gorks() {
    wx.navigateTo({
      url: '../buy-works/buy-works',
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    this.getDataer();
    // var _this = this;
    // _this.setData({
    //   swiper_height: _this.data.goodsArr.length * 325 + 170
    // });
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
    // this.setData({
    //   page:1,
    //   goodsArr:[]
    // });
    // this.getGoods();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    // 上拉加载
    // if(this.data.putBull){
    //   let page = this.data.page;
    //   page++;
    //   this.setData({
    //     page:page
    //   })
    //   this.getGoods();
    // }else{

    // }

  },
  // cui

  select(e) {
    let index = e.currentTarget.dataset.index;
    this.setData({
      swiperIndex: index,
      // page:1,
      // goodsArr:[],
    });
    // if (index == 0){
    //   this.getGoods();      
    // }
  },
  // 获取摄影师信息
  getDataer() {
    wx.request({
      url: url + 'Author/authorDetails',
      data: {
        id: this.data.id,
        token: this.data.mydata.token
      },
      success: (res) => {
        console.log(res);
        if (res.data.code == 0) {
          // 判断头像是否是upload还是http
          if (res.data.data.author.img.indexOf("uploads") >= 0) {
            this.setData({
              imgBull: true
            })
          } else {
            this.setData({
              imgBull: false
            })
          }
          this.setData({
            author: res.data.data.author,
            total: res.data.data.goods_num,
            is_author: res.data.data.is_author
          });
          this.getGoods();
        } else {
          wx.showToast({
            title: res.data.msg,
            icon: "none",
          })
        }
      }
    })
  },
  // 获取作品详情
  getGoods() {
    //加载动画
    wx.showLoading({
      title: "加载中",
      mask: true
    });
    wx.request({
      url: url + 'Author/goodsList',
      data: {
        token: this.data.mydata.token,
        page: this.data.page,
        author_no: this.data.author.author_no
      },
      success: (res) => {
        console.log(res);
        if (res.data.code == 0) {
          // let arr = this.data.goodsArr;
          // if (res.data.data.data.length <10){
          //   this.setData({
          //     putBull:false
          //   })
          // }else{
          //   this.setData({
          //     putBull: true
          //   })
          // }
          // arr = arr.concat(res.data.data.data);
          this.setData({
            goodsArr: res.data.data.goods,
            ids: res.data.data.ids,
            // swiper_height: arr.length * 335 + 15
          })

        } else {
          wx.showToast({
            title: res.data.msg,
            icon: "none"
          })
        }
        wx.hideLoading();
      }
    })
  },
  // 回到首页
  jumpHome() {
    wx.switchTab({
      url: '../home/home'
    })
  },
  // 换购作品
  jumpBuyWork() {
    wx.navigateTo({
      url: '../buy-works/buy-works?author_no=' + this.data.author.author_no + "&author_id=" + this.data.author.id,
    })
  }
  // cui
})