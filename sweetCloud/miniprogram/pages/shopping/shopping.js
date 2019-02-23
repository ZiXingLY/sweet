// pages/shopping/shopping.js
const app = getApp();
var url = app.globalData.netUtil.ROOT_URL;;
var imgUrl = app.globalData.imgUrl;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgUrl: imgUrl,//图片拼接地址
    all:false,//是否全选
    arrData1:[],
    money:0,
    allNum:0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //同步取缓存
    let mydata = wx.getStorageSync("token");
    this.setData({
      mydata: mydata
    });
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
    this.getShop();
    this.setData({
      all:false,
      money: 0,
      allNum: 0,
    })           
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

//  获取购物车列表
  getShop(){
    //加载动画
    wx.showLoading({
      title: "加载中",
      mask: true
    });
    wx.request({
      url: url + 'Shopping/getList',
      data:{
        token:this.data.mydata.token,
      },
      success:(res)=>{
        console.log(res);
        if(res.data.code == 0){
          wx.hideLoading();                  
          this.setData({
            arrData1:res.data.data,
            allNum:0,
            money:0,
          })
        }else{
          wx.hideLoading();                  
          wx.showToast({
            title: res.data.msg,
            icon:"none"
          })
        };
      }
    })
  },
  // 是否全选
  allSelect(){
    let data = this.data.arrData1;
    let money = this.data.money;
    let allNum = this.data.allNum;
    if(this.data.all){
      for (let i = 0; i < data.length; i++) {
        data[i].bull = false
      }
      this.setData({
        all: false,
        arrData1: data,
        money:0,
        allNum:0
      })
    }else{
      money = 0;
      allNum = 0;
      for (let i = 0; i < data.length; i++) {
        if (data[i].is_unable == 1){
          data[i].bull = true
          money = (Number(money) + (Number(data[i].current_price) * Number(data[i].num))).toFixed(2);
          allNum += data[i].num;
        }
      }
      this.setData({
        all: true,
        arrData1: data,
        money:money,
        allNum:allNum,
      })
    }
  },
  // 选择商品
  selectShop(e){
    var item = e.currentTarget.dataset.item;
    var index = e.currentTarget.dataset.index;
    var data1 = this.data.arrData1;
    data1[index].bull = !data1[index].bull;        
    var money = 0;
    var allNum = 0;
    if (data1[index].is_unable == 2){
      wx.request({
        url: url + 'Shopping/is_unable',
        method:"POST",
        data:{
          token: this.data.mydata.token,
          goods_id: item.goods_id,
          version: item.goods_version,
          goods_num:item.num,
          frame_id: item.frame_id ? item.frame_id:'',
          paper_id: item.paper_id ? item.paper_id:'',
          is_print: item.is_print,
        },
        success:(res)=>{
          console.log(res);
          if(res.data.code == 0){
          }else{
            if (data1[index].bull){
              wx.showToast({
                title: res.data.msg,
                icon: "none",
                mask: true,
                duration: 2000
              });
            }else{
              
            }
            
          }
        }
      })
    }else{};
    for (let i = 0; i < data1.length; i++) {
      if (data1[i].bull) {
        money = (Number(money) + (Number(data1[i].current_price) * Number(data1[i].num) )).toFixed(2);
        allNum += data1[i].num;
        this.setData({
          all: true,
        });
      }
    }
    for (let i = 0; i < data1.length; i++) {
      if (!data1[i].bull) {
        this.setData({
          all: false,
        });
        break;
      }
    };
    this.setData({
      money: money,
      arrData1: data1,
      allNum: allNum
    }); 
  },
  // -
  reduce(e){
    var id = e.currentTarget.dataset.id;
    var index = e.currentTarget.dataset.index;
    var data1 = this.data.arrData1;
    var money = 0;
    var allNum = 0;
    data1[index].num --;
    if (data1[index].num <= 1){
      data1[index].num = 1
    }
    wx.request({
      url: url +'Shopping/edit_num',
      data:{
        token:this.data.mydata.token,
        num: data1[index].num,
        id:id,
      },
      success:(res)=>{
        console.log(res);
      }
    })
    for (let i = 0; i < data1.length; i++) {
      if (data1[i].bull) {
        money  = (Number(money) + (Number(data1[i].current_price) * Number(data1[i].num))).toFixed(2);
        allNum += data1[i].num;
      } else {
        
      }
    }
    this.setData({
      arrData1:data1,
      money:money,
      allNum: allNum
    });
    console.log(this.data.money)
  },
  // +
  add(e){ 
    var id = e.currentTarget.dataset.id;
    var index = e.currentTarget.dataset.index;
    var data1 = this.data.arrData1;
    var money = 0;
    var allNum =0;
    // 如果是收藏版就可以加数量，不是的话数量只能为1
    if (data1[index].goods_version == 1){
      if (data1[index].num == 30){
        return;
      }
      
      wx.request({
        url: url + 'Shopping/edit_num',
        data: {
          token: this.data.mydata.token,
          num: data1[index].num,
          id: id,
        },
        success: (res) => {
          if(res.data.code == 0){
            data1[index].num++;
          }else{
            wx.showToast({
              title:res.data.msg,
              icon:"none",
              mask:true
            });
            return;
          }
        }
      })
      for (let i = 0; i < data1.length; i++) {
        if (data1[i].bull) {
          money  = (Number(money) + (Number(data1[i].current_price) * Number(data1[i].num))).toFixed(2);
          allNum += data1[i].num;
        } else {

        }
      }
      this.setData({
        money: money,
        arrData1: data1,
        allNum: allNum
      });
    }else{
      wx.showToast({
        title: '非收藏版只能购买一个',
        icon:"none",
        mask:true,
        duration:2000
      })
    }
    // console.log(this.data.money)
  },
  // 删除
  delate(){
    var data = this.data.arrData1;
    var idStr = "";
    for (let i = 0; i < data.length; i++) {
      if (data[i].bull) {
        idStr += data[i].id + ","
      };
    };
    idStr = idStr.substring(0, idStr.length - 1);
    if (idStr){
      wx.showModal({
        title: '删除',
        content: '确定删除该商品?',
        success: (res) => {
          if (res.confirm) {
            wx.request({
              url: url + 'Shopping/delete',
              method: "DELETE",
              data: {
                token: this.data.mydata.token,
                id: idStr
              },
              success: (res) => {
                if (res.data.code == 0) {
                  this.getShop();
                } else {
                  wx.showToast({
                    title: res.data.msg,
                    icon: "none",
                    mask: true,
                    duration: 2000
                  })
                }
              }
            })
          } else if (res.cancel) {
            console.log('用户点击取消')
          }
        }
      })
    }else{
      return;
    }
     
  },
  // 结算
  sumbit(){
    let data = this.data.arrData1;
    var idStr = "";
    for (let i = 0; i < data.length; i++) {
      if (data[i].bull) {
        idStr += data[i].id + ","
      };
    };
    idStr = idStr.substring(0, idStr.length - 1);
    if (idStr){
      wx.request({
        url: url + 'Shopping/logistics',
        data: {
          token: this.data.mydata.token,
          cart_id: idStr
        },
        success: (res) => {
          console.log(res);
          if(res.data.code == 0){
            wx.navigateTo({
              url: '../sureOrder/sureOrder?idStr=' + idStr + "&type=2",
            })
          }else{
            wx.showToast({
              title: res.data.msg,
              icon: "none",
              mask: true,
              duration: 2000
            });
            return;
          }
        }
      })
      
    }else{
      wx.showToast({
        title: '请选择商品',
        icon: "none",
        mask: true,
        duration: 2000
      });
      return;
    } 
    
  }
})