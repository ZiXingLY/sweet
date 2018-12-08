// pages/addPromise/addPromise.js
const ROOT_URL = getApp().globalData.netUtil.ROOT_URL;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    initValue: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    wx.getStorage({
      key: 'currentUserOpenId',
      success(res) {
        console.log(res.data)
      },
      fail(res) {
        console.log('get key fail')
        wx.login({
          success(res) {
            if (res.code) {
              //发起网络请求
              wx.request({
                url: ROOT_URL + 'user/getOpenid',
                data: {
                  code: res.code
                },
                success: (res) => {
                  console.log(res);
                  if(res.data.code == 0){
                    wx.setStorage({
                      key: "currentUserOpenId",
                      data: res.data.data
                    })
                  }
                }
              })
            } else {
              console.log('登录失败！' + res.errMsg)
            }
          }
        })
      }
    })
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
  onShareAppMessage: function () {

  },

  bindFormSubmit(e){
    console.log(e.detail.value.promise)

    wx.getStorage({
      key: 'currentUserOpenId',
      success(res) {
        console.log(res.data);
        wx.request({
          url: ROOT_URL + 'sys/promise/add',
          data: {
            uuid: res.data,
            promise: e.detail.value.promise
          },
          method: 'POST',
          header: {
            'content-type': 'application/json' // 默认值
          },
          success(res) {
            console.log(res.data)
          }
        })
      }
    })
  }
})