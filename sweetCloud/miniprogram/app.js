//app.js
const _api = require('./api/api.js')
// const wxParse = require('/lib/wxParse/wxParse.js');
App({
  onLaunch: function () {

    
    if (!wx.cloud) {
      console.error('请使用 2.2.3 或以上的基础库以使用云能力')
    } else {
      wx.cloud.init({
        traceUser: true,
      })
    }


    // getOpenid(){
      wx.login({
        success(res) {
          console.log(res)
          _api.getOpenid({ code: res.code }, (res) => {
            console.log(res)
            if (res.code == 0) {
              wx.setStorageSync('openid', res.data)
console.log()
              _api.wxLogin({
                openid: res.data
              }, (res) => {
                console.log(res);
                console.log(res.data.data)
                wx.setStorageSync('token', res.data.data)
                // console.log(res.header['Set-Cookie'])
                // wx.setStorageSync('token', res.header['Set-Cookie'])
              })
            }
          })
        }
      })
    // }

    if(wx.getStorageSync('openid')){
      console.log(wx.getStorageSync('openid'))
    }else{
      wx.login({
        success(res) {
          if (res.code) {

            wx.setStorageSync('openid', res.code)

            console.log(res)
            发起网络请求
            wx.request({
              url: ROOT_URL + '/user/getOpenid',
              data: {
                code: res.code
              },
              success: (res) => {
                console.log(res);
              }
            })
          } else {
            console.log('登录失败！' + res.errMsg)
          }
        }
      })
    }

    this.globalData = {
      wxParse: require("./service/parse.js"),
      timeToTime: require("./service/timer.js"),
      netUtil: require("./service/net.js"),
      api: _api,
      // vxParse: wxParse
    }

    // _api.wxLogin({
    //   openid: wx.getStorageSync('openid')
    // }, (res) => {
    //   console.log(res);
    //   console.log(res.header['Set-Cookie'])
    //   wx.setStorageSync('token', res.header['Set-Cookie'])
    // })
  }
})
