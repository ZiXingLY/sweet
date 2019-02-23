// components/goods-list/goods-list.js
const app = getApp();
var url = app.globalData.netUtil.ROOT_URL;
var imgUrl = app.globalData.imgUrl;
Component({
  /**
   * 组件的初始数据
   */
  data: {
    type: ["自然", "人文", "时尚","当代"],
    imgUrl: imgUrl,
    dataArr: [],
    dataStr: ''
  },
  /**
   * 组件的属性列表
   */
  properties: {
    dataArr: {
      type: Array,
      value: [1, 2, 3, 4],
      observer: function (newVal, oldVal) {
        this.setData({
          dataArr: newVal,
        })
        // console.log(newVal)
      }
    },
    dataStr: {
      type: String,
      value: '1',
      observer: function (newVal, oldVal) {
        // console.log(newVal);
        // console.log(oldVal);
      }
    }
  },


  /**
   * 组件的方法列表
   */
  methods: {
    /**
     * 
     */
    _goDetail: function(e) {
      this.triggerEvent("goDetail", e)
    }
  }
})
