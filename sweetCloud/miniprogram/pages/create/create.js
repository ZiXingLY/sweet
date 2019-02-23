// miniprogram/pages/create/create.js
const ROOT_URL = getApp().globalData.netUtil.ROOT_URL;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    initValue: '',
    index: 0,
    array: ['心情', '时刻','秘密'],
  },

  bindPickerChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)

    if (e.detail.value == 2){
      console.log('to addGoods');
      wx.navigateTo({
        url: '../addGoods/addGoods',
      })
    }
    this.setData({
      index: e.detail.value
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  },
// 用户提交emotion输入
  saveEmotion: function(e){

    // const db = wx.cloud.database();
    if (e.detail.value.emotion == null || e.detail.value.emotion == '') {
      wx.showToast({
        title: '嘿嘿，写点什么吧！',
        icon: 'none'
      })
      return;
    }
    console.log(e.detail.value.emotion)

    getApp().globalData.api.saveEmotion({ 
      'Cookie': wx.getStorageSync('token'),
      'x-auth-token': wx.getStorageSync('token') 
      }, { 
        content: e.detail.value.emotion
        },(res) => {
      if(res.code == 0){
        wx.showToast({
          title: '发布成功',
        })
      }
    })

    // const requestTask = wx.request({
      // url: ROOT_URL + '/emotions/add',
      // method: 'POST',
      // header: {
      //   Cookie: wx.getStorageSync('token')
      // },
      // data: {
      //   content: e.detail.value.emotion,
      // },
      // success: (res) => {
      //   console.log(res.header['Set-Cookie'])
      //   console.log(res)
      //   if(res.data.code == 403){
      //     wx.showModal({
      //       title: '提示',
      //       content: '请先登录',
      //       success(res) {
      //         if (res.confirm) {
      //           console.log('用户点击确定')
    //           } else if (res.cancel) {
    //             console.log('用户点击取消')
    //           }
    //         }
    //       })
    //     }
    //   },
    //   fail: (res) => {
    //     console.log(res)
    //   },
    //   complete: (res) => {
    //     console.log(res)
    //   }
    // })

    // requestTask.onHeadersReceived(res => {
    //   console.log(res);
    // })
    // db.collection('emotions').add({
    //   data: {
    //     content: e.detail.value.emotion,
    //     createTime: db.serverDate(),
    //     // avatarUrl: 
    //   }
    // }).then(res => {

    //   wx.switchTab({
    //     url: '../home/home',
    //   })
    //   this.setData({
    //     initValue: ''
    //   })
    //   // wx.navigateTo({
    //   //   url: '../home/home',
    //   // })
    //   console.log(res)
    // })
  },


  saveMoment: function(e){

    const db = wx.cloud.database();
    let remoteImgList = [];

    db.collection('moments').add({
      data: {
        // content: e.detail.value.emotion,
        createTime: db.serverDate(),
        // avatarUrl: 
      }
    }).then(res => {
      console.log(res)

      let _id = res._id;

      console.log(e.detail.value.paths)

      for (let i = 0; e.detail.value.paths && i < e.detail.value.paths.length; i++) {
        // console.log(e.detail.value.paths[i])

        wx.cloud.uploadFile({
          cloudPath: 'uploads/img/' + new Date().getTime() + '.png',
          filePath: e.detail.value.paths[i], // 小程序临时文件路径
        }).then(_res => {
          // get resource ID
          console.log(_res.fileID)
          remoteImgList.push(_res.fileID)

          db.collection('moments').doc(_id).update({
            // data 传入需要局部更新的数据
            data: {
              // 表示将 done 字段置为 true
              paths: remoteImgList
            }
          }).then(__res => {
            wx.showToast({
              title: '成功上传'+i+1+'张图',
            })
            console.log(__res)
          }).catch(console.error)
        }).catch(error => {
          console.log(error)
          // handle error
        })

      }

    })



  },
  bindFormSubmit: function (e) {

    if (this.data.index == 0){
      this.saveEmotion(e)
    }
    else if (this.data.index == 1){
      this.saveMoment(e)
    }else{
      console.log('type error!')
    }

    // const db = wx.cloud.database();
    // if (e.detail.value.emotion == null || e.detail.value.emotion == ''){
    //   wx.showToast({
    //     title: '嘿嘿，写点什么吧！',
    //   })
    //   return;
    // }


    // console.log(e.detail.value.emotion)
    // db.collection('emotions').add({
    //   data: {
    //     content: e.detail.value.emotion,
    //     createTime: db.serverDate(),
    //     // avatarUrl: 
    //   }
    // }).then(res => {

    //   let _id = res._id;
    //   let remoteImgList = [];

    //   console.log(e.detail.value.paths)

    //   for (let i = 0; e.detail.value.paths && i < e.detail.value.paths.length; i++){


    //     console.log(e.detail.value.paths[i])


    //     wx.cloud.uploadFile({
    //       cloudPath: 'uploads/img/' + new Date().getTime() + '.png',
    //       filePath: e.detail.value.paths[i], // 小程序临时文件路径
    //     }).then(_res => {
    //       // get resource ID
    //       console.log(_res.fileID)
    //       remoteImgList.push(_res.fileID)

    //       db.collection('emotions').doc(_id).update({
    //         // data 传入需要局部更新的数据
    //         data: {
    //           // 表示将 done 字段置为 true
    //           paths: remoteImgList
    //         }
    //       }).then( __res =>{
    //         console.log(__res)
    //       }).catch(console.error)
    //     }).catch(error => {
    //       console.log(error)
    //       // handle error
    //     })

    //   }

    //   console.log(res)
    //   wx.showToast({
    //     title: '内容上传成功',
    //   })
    //   console.log(e.detail.value);
    // })
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