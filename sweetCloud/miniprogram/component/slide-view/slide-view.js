// components/slide-view/slide-view.js
const app = getApp();
var url = app.globalData.servsers;
var imgUrl = app.globalData.imgUrl;
Component({

  /**
   * 组件的初始数据
   */
  data: {
    imgUrl: imgUrl,
    // dataArr: [1,2,3]
  },

  /**
   * 组件的属性列表
   */
  properties: {
    dataArr: {
      type: Array,
      value: [1,2,3],
      observer: function (newVal, oldVal) {
        this.setData({
          dataArr: newVal
        })
      }
    }
  },

  


  // attached: function () {
  //   let a = 111;
  //   console.log(a);
  //   console.log(this.dataArr);
  //   this.setData({
  //     dataArr: this.dataArr
  //   })
  // },

  onLoad: function (e) {
  },

  ready: function () {
  },

  /**
   * 组件的方法列表
   */
  methods: {
    _getItem: function (e) {
      this.triggerEvent("getItem",e)
    }
  }
})
