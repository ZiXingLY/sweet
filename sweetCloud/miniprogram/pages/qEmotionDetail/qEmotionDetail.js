// miniprogram/pages/qEmotionDetail/qEmotionDetail.js

const wxParse = getApp().globalData.wxParse;
const timestampToTime = getApp().globalData.timeToTime.timestampToTime;
const ROOT_URL = getApp().globalData.netUtil.ROOT_URL;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    emotion: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showNavigationBarLoading();
    // console.log(options.tid);
    // console.log(options.type);
    if(options.type == 3){
      this.getWallData(options.tid)
    }else if(options.type == 4){
this.getTiebaData(options.tid)
    }

  },

  getTiebaData(tid){

    console.log(tid)
    let _this = this;

    wx.request({
      url: ROOT_URL + '/sys/sweet/tieba/detail?tid=' + tid,
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        item = res.data.data;

        console.log(item)

        item.createTime = timestampToTime(item.created);
        item.content = wxParse.strDiscode(item.content);
        item.authorImg = '../../images/user.gif';
        item.authorName = '贴吧君';
        item.info = JSON.parse(item.info);
        console.log(item);

        _this.setData({
          emotion: item
        })
        wx.hideNavigationBarLoading();

        }
    })

  },

  getWallData(tid){

    console.log(tid)
    let _this = this;

    wx.request({
      url: ROOT_URL + '/sys/sweet/wall/detail?tid=' + tid,
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {

        item = res.data.data;

        console.log(item)

        item.createTime = timestampToTime(item.created);
        item.content = wxParse.strDiscode(item.content);
        item.authorImg = '../../images/user.gif';
        item.authorName = '表白墙';
        item.info = JSON.parse(item.info);
        console.log(item);

        _this.setData({
          emotion: item
        })
wx.hideNavigationBarLoading();
        // res.data.data.list.map(
        //   item => {
        //   // item.createTime = timestampToTime(item.createTime.$date);
        //   item.content = wxParse.strDiscode(item.content);
        //   item.authorImg = '../../images/user.gif';
        //   item.authorName = '表白墙';
        //   item.info = JSON.parse(item.info)
        //   console.log(item)
        //   return item;
        // }
        // )

        // let conver = res.data.data.list.map(item => {
        //   item.author = wxParse.strDiscode(item.info.name);

        //   if (item.info.commentlist) {
        //     console.log('format commentList!')
      //       item.info.commentlist.map(record => {
      //         record.content = wxParse.strDiscode(record.content);
      //         record.name = wxParse.strDiscode(record.name);
      //         return record
      //       })
      //     }
      //     return item;
      //   }
      //   )
        // _this.setData({
        //   walls: res.data.data.list,
        //   wallPage: ++_this.data.wallPage
        // })
      //   console.log(res.data.data.list[0].info)
      // }
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

  }
})