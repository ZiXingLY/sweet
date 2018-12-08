// miniprogram/pages/deviceMotion/deviceMotion.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    device: false,
    ap: '',
    bt: '',
    gm: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  showMotion: function(e){

    // console.log(e.detail)

    if (e.detail.value){
      wx.startDeviceMotionListening({
        // interval: 'ui',
        success: (res) => {
          console.log('打开方向监听')
        }
      })

      wx.onDeviceMotionChange((res) => {

        this.setData({
          ap: res.alpha,
          bt: res.beta,
          gm: res.gamma
        })
        console.log(res)
      })

      this.setData({
        device: true
      })
    }else{
      wx.stopDeviceMotionListening({
        success: ()=>{
          console.log('关闭方向监听')
        }
      })
    }
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