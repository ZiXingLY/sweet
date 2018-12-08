// miniprogram/pages/cet/cet.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    name: '',
    noValue: ''
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

  copyNeea(){
    wx.setClipboardData({
      data: 'http://cet.neea.edu.cn/cet',
      success: function(){
        wx.showToast({
          title: '复制成功',
        })
      }
    })
  },

  copyChsi() {
    wx.setClipboardData({
      data: 'http://cet.neea.edu.cn/cet',
      success: function () {
        wx.showToast({
          title: '复制成功',
        })
      }
    })
  },

  nameInput(e){
    // console.log(e)
    this.setData({
      name: e.detail.value
    })
  },

  noInput(e){
    this.setData({
      noValue: e.detail.value
    })
    // console.log(e)
  },

  bindFormSubmit(e) {
    console.log(e.detail.value.name)
  }
})