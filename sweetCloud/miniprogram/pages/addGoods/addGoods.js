// pages/addGoods/addGoods.js
const _api = getApp().globalData.api;
Page({

  /**
   * 页面的初始数据
   */
  data: {

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
  bindFormSubmit(e) {
    const title = e.detail.value.title;
    const info = e.detail.value.info;
    const paths = e.detail.value.paths;
    console.log(e.detail)
    _api.addGoods({
      'x-auth-token': wx.getStorageSync('token')
    },{
      title: title,
      image: paths[0],
      info: info
    },(res) => {
      console.log(res)
      if(res.code == 0){
        wx.showToast({
          title: '添加成功',
          icon: 'none'
        })
      }
    })
  },
  onPathLoad(e){
    console.log(e);
  }
})