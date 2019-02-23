// component/picture/sweet-picture-upload.js
Component({
  behaviors: ['wx://form-field'],
  /**
   * 组件的属性列表
   */
  properties: {
    Paths: {
      type: Array,
      value: ['']
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
  
    chooseImg() {
      
      let _this = this;
      let paths;
      wx.chooseImage({
        count: 9,
        sizeType: ['original', 'compressed'],
        sourceType: ['album', 'camera'],
        success(res) {
          // tempFilePath可以作为img标签的src属性显示图片
          // const tempFilePaths = res.tempFilePaths
          console.log(res.tempFilePaths)
          paths = res.tempFilePaths;
          _this.triggerEvent('pathload', { paths: paths},{})
          _this.setData({
            value: paths
          })
          paths.push('')
          _this.setData({
            Paths: paths
          })
        }
      })
      console.log(this.data.Paths)
    }
  }
})
