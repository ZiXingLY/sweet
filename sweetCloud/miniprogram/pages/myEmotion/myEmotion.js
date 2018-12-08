// pages/article/article.js
const app = getApp();
const wxParse = getApp().globalData.wxParse;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    params: {
      pageStart: 0
    },
    isInfinite: false,
    emotions: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    wx.setNavigationBarTitle({
      title: '心情',
    });
    wx.showNavigationBarLoading();
    wx.cloud.init()
  },
  // goToShare(e) {
  //   console.log(e.currentTarget.dataset.data);
  //   wx.setStorageSync('flash', JSON.stringify(e.currentTarget.dataset.data));
  //   wx.navigateTo({
  //     url: '../flash-share/flash-share?data=1',
  //   })
  // },
  getEmotions(){
    wx.cloud.callFunction({
      name: 'readEmotions',
      data:{
        start: 1
      }
    }).then(res => {
      console.log(res.result.data)
      let list = res.result.data.map( item => {
        item.createTime = this.timestampToTime(item.createTime.$date);
        item.content = wxParse.strDiscode(item.content);
        return item;
      });
      // let isInfinite;
      console.log(list)
      this.setData({
        emotions: list
      })
      wx.hideNavigationBarLoading();
    })
  },
  getFlash(flag, isRefresh) {
    app.globalData.api.flashNews(this.data.params, (res) => {
      console.log(res['data']['data']);
      let fList = res['data']['data']['list'];
      let isInfinite,
        data = [];
      for (let i = 0; i < fList.length; i++) {
        fList[i]['created'] = this.timestampToTime(fList[i]['created']);
        fList[i]['content'] = fList[i]['content'].replace(/\<img/gi, '<img class="rich-img" ');
        fList[i]['content'] = getApp().globalData.wxParse.strDiscode(fList[i]['content']);
      };
      if (isRefresh) {
        wx.stopPullDownRefresh();
      }
      if (fList.length >= 5) {
        isInfinite = true;
      } else {
        isInfinite = false;
      }
      if (flag) {
        data = this.data.flashData.concat(fList);
      } else {
        data = fList;
      }
      // console.log(data);
      this.setData({
        flashData: data,
        isInfinite: isInfinite
      })
    })
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
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

    this.getEmotions()
    // let params = this.data.params;
    // params['pageStart'] = 0;
    // this.setData({
    //   params: params
    // })
    // this.getFlash();
    // let cookie = wx.getStorageSync("cookie");
    // 获取消息
    // getApp().globalData.api.newsList({ "cookie": cookie }, {}, (res) => {
    //   console.log(res);
    //   let history = wx.getStorageSync("historyMessage");
    //   console.log(history);
    //   if (res['code'] == 0 && history) {
    //     if (history[0]['add_time'] == res['data']['list'][0]['add_time'] && history[0]['id'] == res['data']['list'][0]['id']) {
    //       console.log("暂无新消息");
    //     } else {
    //       wx.showTabBarRedDot({
    //         index: 2
    //       })
    //     }
    //   }
    // })
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
  onPullDownRefresh() {
    this.getEmotions();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {
    this.getEmotions();
    // let params = this.data.params;
    // if (this.data.isInfinite) {
    //   params['pageStart']++;
    //   this.getFlash(true);
    // }
  },

})