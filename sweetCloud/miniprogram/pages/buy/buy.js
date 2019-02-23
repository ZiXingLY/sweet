const app = getApp();
const url = app.globalData.netUtil.ROOT_URL;
var imgUrl = app.globalData.imgUrl;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    options: {},
    scrollTop: 0,
    imgUrl: imgUrl,//图片拼接地址
    // 搜索栏背景色透明度
    opacity: 0.5,
    // 轮播图数据
    dataArr: [
      'banner_01.jpeg',
      'banner_02.jpeg'
    ],
    // 摄影师数据
    imgArr: [],
    // 作品数据
    goodsArr: [],
    ids: [],
    userShow: wx.canIUse('button.open-type.getUserInfo'),//判断用户是否授权
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options);
    if (options.shareType) {
      wx.setStorageSync("shareData", {
        options: options
      });
    }
    //同步取缓存
    let mydata = wx.getStorageSync("token");
    // let shareData = wx.getStorageSync("shareData");
    // console.log(shareData);
    this.setData({
      mydata: mydata,
      options: options
    });

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    // this.banner = this.selectComponent("#banner");
    // this.goods = this.selectComponent("#goods");
    this.checkSessions();
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    // 是否有新的消息
    let a1 = setTimeout(() => {
      this.ifNotic();
      this.creatPhotographer();
      clearTimeout(a1);
    }, 500);
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
  onShareAppMessage: function () {
    return {
      title: "2019，我的影像价值观"
    }
  },
  /**
   * 获取轮播图的详情
   */
  _getItem: function (e) {
    var item = e.detail.currentTarget.dataset.bind;
    console.log(item);
    // 关联活动类型：3不关联，2促销活动，1助力
    if (item.type == 1) {
      wx.request({
        url: url + 'Banner/Theme?type=1',
        data: {
          token: this.data.mydata.token,
          type: 1,
          theme_no: item.theme_no
        },
        success: (res) => {
          if (res.data.code == 0) {
            wx.navigateTo({
              url: "/pages/swiper_help/swiper_help?item=" + JSON.stringify(item) + "&jumpType=1"
            })
          } else {
            wx.showToast({
              title: res.data.msg,
              icon: "none",
              mask: true
            })
          }
        }
      })

    } else if (item.type == 2) {
      wx.request({
        url: url + 'Banner/Theme?type=2',
        data: {
          token: this.data.mydata.token,
          type: 2,
          theme_no: item.theme_no
        },
        success: (res) => {
          if (res.data.code == 0) {
            var id = res.data.data.goods.id;
            wx.navigateTo({
              url: "/pages/goods-detail/goods-detail?theme_no=" + item.theme_no + "&jumpType=lunbotu" + "&id=" + id + "&ids=" + JSON.stringify(res.data.data.ids) + "&theme_id=" + res.data.data.theme_id
            })
            console.log(res);
          } else {
            wx.showToast({
              title: res.data.msg,
              icon: "none",
              mask: true
            })
          }
        }
      })
    }
  },

  /**
   * 获取商品的id
   */
  // 点击本周推荐作品大图进入详情
  _goDetail(e) {
    let ids = JSON.stringify(this.data.ids);
    wx.navigateTo({
      url: '../goods-detail/goods-detail?id=' + e.currentTarget.dataset['id'] + "&ids=" + ids,
    })
  },
  // 点击推荐作品列表进入详情
  _getDetail: function (e) {
    let ids = JSON.stringify(this.data.ids);
    wx.navigateTo({
      url: '../goods-detail/goods-detail?id=' + e.detail.currentTarget.dataset['id'] + "&ids=" + ids,
    })
  },
  // 检查是否授权
  checkSessions() {
    var options = wx.getStorageSync("shareData").options;
    wx.getSetting({
      success: (res) => {
        if (res.authSetting["scope.userInfo"]) {
          // 如果已经绑定手机号，判断是否是点击链接进来
          let isData = wx.getStorageSync("token");
          if (isData.tel == "" && isData.is_once) {
            // 如果没有绑定手机号，跳转到绑定手机号
            wx.navigateTo({
              url: '/pages/bindTel/bindTel',
            });
            return;

          } else {
            // 检测是否是从其他页面过来
            if (options) {
              console.log("我要开始调用领取接口啦，啦啦啦");
              if (options.shareType == "zengsongzuopin") {
                wx.request({
                  url: url + 'Mygoods/lead',
                  method: "POST",
                  data: {
                    token: this.data.mydata.token,
                    photo_no: options.photo_no ? options.photo_no : "",
                    goods_id: options.goods_id,
                    unionId: options.unionId,
                    addTime: options.addTime
                  },
                  success: (res) => {
                    if (res.data.code == 0) {
                      wx.showModal({
                        title: '提示',
                        content: "恭喜您领取成功，请到“我的->我的藏品”中查看",
                        showCancel: false,
                        success: (res) => {
                          if (res.confirm) {
                            wx.removeStorageSync("shareData");
                          } else if (res.cancel) {

                          }
                        }
                      })
                    } else {
                      wx.showModal({
                        title: '提示',
                        content: res.data.msg,
                        showCancel: false,
                        success: (res) => {
                          if (res.confirm) {
                            wx.removeStorageSync("shareData");
                          } else if (res.cancel) {

                          }
                        }
                      })
                    }
                  }
                })
              } else if (options.shareType == "zengsongcangpin") {
                wx.request({
                  url: url + 'Collection/receive',
                  method: "POST",
                  data: {
                    token: this.data.mydata.token,
                    id: options.id,
                    addTime: options.addTime,
                    unionId: options.unionId,
                    goods_version: options.goods_version,
                    version_no: options.version_no
                  },
                  success: (res) => {
                    if (res.data.code == 0) {
                      wx.showModal({
                        title: '提示',
                        content: "恭喜您领取成功,请到‘我的藏品’ 中查看",
                        showCancel: false,
                        success: (res) => {
                          if (res.confirm) {
                            wx.removeStorageSync("shareData");
                          } else if (res.cancel) {

                          }
                        }
                      })
                    } else {
                      wx.showModal({
                        title: '提示',
                        content: res.data.msg,
                        showCancel: false,
                        success: (res) => {
                          if (res.confirm) {
                            wx.removeStorageSync("shareData");
                          } else if (res.cancel) {

                          }
                        }
                      })
                    }
                  }
                })
              } else if (options.shareType == "zhuli") {
                wx.removeStorageSync("shareData");
                wx.navigateTo({
                  url: "/pages/swiper_help/swiper_help?openid=" + options.openid + "&jumpType=2&theme_no=" + options.theme_no,
                })
              } else if (options.shareType == "dazhe") {
                wx.removeStorageSync("shareData");
                wx.navigateTo({
                  url: "/pages/goods-detail/goods-detail?id=" + options.id + "&ids=" + options.ids + "&discount=" + options.discount + "&jumpType=dazhe"
                })
              }
            }
          }
        } else {
          wx.reLaunch({
            url: "../authorization/authorization"
          });
          return;
        }
      }
    })
  },
  // 查看是否有未读消息
  ifNotic() {
    wx.request({
      url: url + 'Home/is_read',
      data: {
        token: this.data.mydata.token
      },
      success: (res) => {
        if (res.data.code == 0) {
          this.setData({
            noticBull: res.data.data
          })
        }
      }
    })
  },
  /**
   * 跳转到摄影师页面
   */
  goPhotographer: function (e) {
    console.log(e.currentTarget.dataset.id)
    wx.navigateTo({
      url: '../photographer/photographer?id=' + e.currentTarget.dataset['id'],
    })
  },
  goSearch: function (e) {
    wx.navigateTo({
      url: '../search-his/search-his',
    })
  },

  // //监听屏幕滚动 判断上下滚动
  // onPageScroll: function (ev) {
  //   var _this = this;
  //   //当滚动的top值最大或最小时，为什么要做这一步是因为在手机实测小程序的时候会发生滚动条回弹，所以为了处理回弹，设置默认最大最小值
  //   if (ev.scrollTop <= 0) {
  //     ev.scrollTop = 0;
  //   } else if (ev.scrollTop > wx.getSystemInfoSync().windowHeight) {
  //     ev.scrollTop = wx.getSystemInfoSync().windowHeight;
  //   }
  //   //判断浏览器滚动条上下滚动
  //   if (ev.scrollTop > this.data.scrollTop || ev.scrollTop == wx.getSystemInfoSync().windowHeight) {
  //     //向下滚动
  //     // wx.hideTabBar({
  //     //   aniamtion: true,
  //     // })
  //   } else {
  //     //向上滚动
  //     // wx.showTabBar({
  //     //   aniamtion: true,        
  //     // })
  //   }
  //   //给scrollTop重新赋值
  //   setTimeout(function () {
  //     _this.setData({
  //       scrollTop: ev.scrollTop,
  //       opacity: ev.scrollTop/100
  //     })
  //   }, 0);
  // },
  /**
   * 跳转到摄影师列表
   */
  go_pg_list() {
    wx.navigateTo({
      url: '../pg-list/pg-list',
    })
  },
  /**
   * 跳转作品列表
   */
  go_works_list() {
    wx.navigateTo({
      url: '../works-list/works-list',
    })
  },
  go_message() {
    wx.navigateTo({
      url: '../message/message',
    })
  },
  // 跳转到purchaseArea换购区页面
  jumpPurchase() {
    if (this.data.is_author == 3) {
      return;
    } else {
      wx.navigateTo({
        url: '../purchaseArea/purchaseArea',
      })
    }
  },


  // 摄影师列表/图片列表 数据
  creatPhotographer() {
    //加载动画
    wx.showLoading({
      title: "加载中",
      mask: true
    });
    let storageData1 = wx.getStorageSync('openid');
    wx.request({
      url: url + '/wx/index',
      data: {
        token: this.data.mydata.token,
        unionId: storageData1
      },
      header: {
        'x-auth-token': wx.getStorageSync('token')
      },
      success: (res) => {
        console.log(res);
        if (res.data.code == 0) {
          //判断评论列表里面的头像是upload还是http
          if (res.data.data.user.length > 0) {
            for (let i = 0; i < res.data.data.user.length; i++) {
              if (res.data.data.user[i].header.indexOf("uploads") >= 0) {
                res.data.data.user[i].assetsimgBull = true;
              } else {
                res.data.data.user[i].assetsimgBull = false;
              };
            }

          } else {

          }
          // 拆分 展示的热门图片数组（大图展示数组第一个）
          if (res.data.data.goods.length > 0) {
            var arrData = res.data.data.goods;
            var goodsArr = arrData.slice(0, 1);
            var surplusArr = arrData.slice(1, arrData.length);
          }
          this.setData({
            imgArr: res.data.data.user,
            goodsArr,
            surplusArr,
            ids: res.data.data.ids,
            swiperArr: res.data.data.banner,
            is_author: res.data.data.is_author
          })
        };
        wx.hideLoading();
      }
    })
  }

})