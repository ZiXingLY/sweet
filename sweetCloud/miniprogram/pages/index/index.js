//index.js
const app = getApp()
const ROOT_URL = getApp().globalData.netUtil.ROOT_URL;
const _api = getApp().globalData.api;
Page({
  data: {
    avatar: '../../images/user.gif',
    avatarUrl: './user-unlogin.png',
    userInfo: {},
    userName: '',
    logged: false,
    takeSession: false,
    requestResult: '',
    imgUrls: []
  },

  onLoad: function() {
    if (!wx.cloud) {
      wx.redirectTo({
        url: '../chooseLib/chooseLib',
      })
      return
    }

    // 初始化轮播图
    // const db = wx.cloud.database()
    // db.collection('slideshow')
    // .where({type: 1})
    // .get()
    // .then(res => {
    //   this.setData({
    //     imgUrls: res.data
    //   })
    //   // console.log(res)
    // })
    // .where({
    //   "type": 1
    // }).get().then(res => {
    //   console.log(res.data)
    // })

    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {

              console.log(res.userInfo)
              this.setData({
                avatarUrl: res.userInfo.avatarUrl,
                userInfo: res.userInfo,
                userName: res.userInfo.nickName
              })
            }
          })
        }
      }
    })
  },

  onGetUserInfo: function(e) {

    console.log(e.detail.code)
    this.getOpenId(e.detail.code);
    if (!this.logged && e.detail.userInfo) {
      this.setData({
        logged: true,
        avatarUrl: e.detail.userInfo.avatarUrl,
        userInfo: e.detail.userInfo
      })
    }
  },

  getOpenId(code){

    // this.globalData.netUtil;
    console.log(app.globalData.netUtil.ROOT_URL);
    wx.request({
      url: app.globalData.netUtil.ROOT_URL +'user/getOpenid',
      data:{
        code: code
      },
      success: (res)=>{
        console.log(res);
      }
    })
  },

  onGetOpenid: function() {
    // 调用云函数
    wx.cloud.callFunction({
      name: 'login',
      data: {},
      success: res => {
        console.log('[云函数] [login] user openid: ', res.result.openid)
        app.globalData.openid = res.result.openid
        wx.navigateTo({
          url: '../userConsole/userConsole',
        })
      },
      fail: err => {
        console.error('[云函数] [login] 调用失败', err)
        wx.navigateTo({
          url: '../deployFunctions/deployFunctions',
        })
      }
    })
  },

  // 上传图片
  doUpload: function () {
    // 选择图片
    wx.chooseImage({
      count: 1,
      sizeType: ['compressed'],
      sourceType: ['album', 'camera'],
      success: function (res) {

        wx.showLoading({
          title: '上传中',
        })

        const filePath = res.tempFilePaths[0]
        
        // 上传图片
        const cloudPath = 'my-image' + filePath.match(/\.[^.]+?$/)[0]
        wx.cloud.uploadFile({
          cloudPath,
          filePath,
          success: res => {
            console.log('[上传文件] 成功：', res)

            app.globalData.fileID = res.fileID
            app.globalData.cloudPath = cloudPath
            app.globalData.imagePath = filePath
            
            wx.navigateTo({
              url: '../storageConsole/storageConsole'
            })
          },
          fail: e => {
            console.error('[上传文件] 失败：', e)
            wx.showToast({
              icon: 'none',
              title: '上传失败',
            })
          },
          complete: () => {
            wx.hideLoading()
          }
        })

      },
      fail: e => {
        console.error(e)
      }
    })
  },


  login(){

    _api.wxLogin({
      openid: wx.getStorageSync('openid')
    }, (res) => {
      console.log(res);
      console.log(res.header['Set-Cookie'])
      wx.setStorageSync('token', res.header['Set-Cookie'])
    })

    // wx.request({
    //   url: ROOT_URL + '/user/wxLogin',
    //   method: 'POST',
    //   data: {
    //     openid: wx.getStorageSync('openid')
    //   },
    //   success: (res) => {
    //     console.log(res);
    //     console.log(res.header['Set-Cookie'])
    //     wx.setStorageSync('token', res.header['Set-Cookie'])
    //   }
    // })

    // wx.login({
    //   success(res) {
    //     if (res.code) {

    //       console.log(res)
    //       // 发起网络请求
    //       wx.request({
    //         url: ROOT_URL +'/user/getOpenid',
    //         data: {
    //           code: res.code
    //         },
    //         success: (res) => {
    //           console.log(res);
    //         }
    //       })
    //     } else {
    //       console.log('登录失败！' + res.errMsg)
    //     }
    //   }
    // })
  },

  getOpenid(){
    wx.login({
      success(res) {
        console.log(res)
        _api.getOpenid({code: res.code}, (res) => {
          console.log(res)
          if (res.code == 0) {
            wx.setStorageSync('openid', res.data)
          }
        })
      }
    })
  }

})
