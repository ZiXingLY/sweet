// miniprogram/pages/emotionDetail/emotionDetail.js

const app = getApp();
const wxParse = getApp().globalData.wxParse;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id: '',
    emotion:{}
  },

  delete: function(){
    const db = wx.cloud.database()
    db.collection('emotions').doc(this.data.emotion._id).remove()
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
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    wx.showNavigationBarLoading();

    const db = wx.cloud.database()
    db.collection('emotions').where({
      _id: options.id
    })
    .get()
    .then(res => {

      console.log(res.data[0])

      let item = res.data[0];

      item.createTime = this.timestampToTime(item.createTime.$date);
      item.content = wxParse.strDiscode(item.content);

      this.setData({
        emotion: item
      })
      wx.hideNavigationBarLoading()
      console.log(res.data[0])
    })

    // this.setData({
    //   id: options.id
    // })
    console.log(options.id)
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
    console.log(this.route)
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


    timestampToTime(timestamp) {
    let date = new Date(timestamp);
    let Y, M, D, h, m;
    Y = date.getFullYear() + '.';
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '.';
    D = date.getDate() + ' ';
    h = (date.getHours() >= 10 ? date.getHours() : '0' + date.getHours()) + ':';
    m = date.getMinutes() >= 10 ? date.getMinutes() : '0' + date.getMinutes();
    return Y + M + D + h + m;
  },
})