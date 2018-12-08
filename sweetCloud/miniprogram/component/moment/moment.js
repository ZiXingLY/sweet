// component/moment/moment.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    // Paths: {
    //   type: Array,
    //   value: ['']
    // },
    Moment: {
      type: Object,
      value: ''
    }
  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   */
  methods: {

    previewImage(e){

      console.log(e.target.dataset.url)

      wx.previewImage({
        urls: [e.target.dataset.url],
      })
    }

  }
})
