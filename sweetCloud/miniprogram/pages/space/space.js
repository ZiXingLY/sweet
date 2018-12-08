// miniprogram/pages/space/space.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

    Secrets: []

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let list = [];
    list.push('<p>基于区块链的不可抵赖，不可篡改性，我们设计了甜甜圈区块。基于认同机制的共识体系，</p>')
    for(let i = 0 ; i < 20; i++){

      list.push('hahhahaha')
      // list.join('hahhahaha')
    }

    console.log(list)
     this.setData({
       Secrets: list
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

  goToAddPromise(){
    wx.navigateTo({
      url: '../addPromise/addPromise',
    })
  }
})