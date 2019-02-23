// pages/addEmotion/addEmotion.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    initValue: '',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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

  save(e){
    if (e.detail.value.emotion == null || e.detail.value.emotion == '') {
      wx.showToast({
        title: '嘿嘿，写点什么吧！',
        icon: 'none'
      })
      return;
    }
    console.log(e.detail.value.emotion)

    getApp().globalData.api.saveEmotion({
      'Cookie': wx.getStorageSync('token'),
      'x-auth-token': wx.getStorageSync('token')
    }, {
        content: e.detail.value.emotion
      }, (res) => {
        if (res.code == 0) {
          wx.showToast({
            title: '发布成功',
          })
        }
      })
  }
})