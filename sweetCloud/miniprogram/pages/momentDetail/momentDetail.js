// miniprogram/pages/momentDetail/momentDetail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    paths: [],
    id: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showNavigationBarLoading();

    this.setData({
      id: options.id
    })

    const db = wx.cloud.database();
    db.collection('moments')
    .where({
      _id: options.id
    })
      // .orderBy('createTime', 'desc')
      .get()
      .then(res => {
        // console.log(res)
        // console.log(res.data)
        // console.log(res.data[0])
        // console.log(res.data[0]['paths'])

        console.log(res.data[0].paths)



        this.setData({
          paths: res.data[0]['paths']
        })

        wx.hideNavigationBarLoading()
      })
  },

  delete: function () {
    const db = wx.cloud.database()
    db.collection('moments').doc(this.data.id).remove()
      .then(res => {
        console.log(res)
        wx.showToast({
          title: '删除成功！',
        })
        console.log('delete')
        // wx.navigateTo({
        //   url: '../home/home',
        // })
        wx.navigateBack({

        })
      })
      .catch(console.error)
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

  }
})