// component/pictureUpload/pictureUpload.js
Component({

  behaviors: ['wx://form-field'],
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    Paths: []
  },

  /**
   * 组件的方法列表
   */
  methods: {

    chooseImg(){

      wx.chooseImage({
        count: 9,
        sizeType: ['original', 'compressed'],
        sourceType: ['album', 'camera'],
        success(res) {
          // tempFilePath可以作为img标签的src属性显示图片
          // const tempFilePaths = res.tempFilePaths
          console.log(res.tempFilePaths)
          this.setData({
            Paths: res.tempFilePaths
          })
        }
      })
    }

  }
})
