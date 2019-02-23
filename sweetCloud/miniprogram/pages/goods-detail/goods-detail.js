// pages/goods-detail/goods-detail.js
const goodsDetail = getApp().globalData.api.goodsDetail
const app = getApp();
var url = app.globalData.netUtil.ROOT_URL;
var imgUrl = app.globalData.imgUrl;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    page:1,
    id:"",
    ids:[],//查看大图的数组
    imgData:{},//详情数据
    photo:[],//详情大图的数据
    photoData:[],//放大图片的 数组数据
    imgUrl: imgUrl,//用来拼接图片的地址
    swiperArr: [],//轮播图数据
    assetsList:[],//评论数据
    wantIndex:0,//查看大图时所处的index
    // cui 简介下标
    swiperIndex:0,
    alertBull:false,//弹窗是否弹出
    Format: ["收藏版", "精品版", "极品版","博物馆版"],//版式
    lunbotu_Format: ["收藏版"],
    selectFormat:"收藏版",//选择的版式
    goodsDate: {},//作品数据
    paperArr: [],//卡纸数据
    frameArr: [],//相框数据	 
    selectCardboard:"",//选择的卡纸
    selectPhotoframe:"",//选择的相框
    selectDY:"不打印",//选择打印或者不打印
    dayinMoneyData:0,//从接口传过来的打印费
    banshiMoney:0,//产生的版式价格
    dayinMoney:0,//产生的打印费
    kazhiMoney:0,//产生的卡纸费用
    xiangkuangMoney:0,//产生的相框费用
    allNum:1,//数量
    price:0,//价格
    kazhiId:"",
    xiangkuangId:"",
    buttonType:"",//弹出框里面的确定按钮是购物车还是立即购买
    tusiState:2,//红点提示，1显示 2不显示
    // ******查看大图
    bigBull: false,//是否显示大图    
    swiperIndex:0,//swiper当前所在位置
    swiperCurrent:"",//swiper应该在的位置
    touch: {
      distance: 0,
      scale: 1,
      baseWidth: null,
      baseHeight: null,
      scaleWidth: null,
      scaleHeight: null
    },
    priceDetailBull:false,//价格详情弹窗bull值
    priceDetail:{},//价格详情数据
    dazheBull:false,//打折弹窗
    dazheNUmber: [
      { name: "九折", discount: 0.9, seldiscount:9},
      { name: "八折", discount: 0.8, seldiscount:8 },
      { name: "七折", discount: 0.7, seldiscount:7},
      { name: "六折", discount: 0.6, seldiscount:6 },
      { name: "五折", discount: 0.5, seldiscount:5 },
     ],
     selectDiscount:"",//选择的打折标签
    seldiscount:"",//选择的标签折扣数（不带0.）
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options);
    let mydata = wx.getStorageSync("token");
    this.setData({
      mydata: mydata,
      // id: options.id,
      // ids:options.ids,
      options:options
    });
    wx.getSystemInfo({
      success: (res)=> {
        this.setData({
          screenHeight: res.windowHeight,
          screenWidth: res.windowWidth,
        });
      }
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
    var options = this.data.options;
    // 如果是从我的作品 过来的
    if (options.jumpType && options.jumpType == "myWorks") {
    } else if (options.jumpType && options.jumpType == "huangou") {
    } else if (options.jumpType && options.jumpType == "cangpin") {    
      // 从我的收藏过来 
    }else{
    }
    this.creatGoods();    
    this.creatAssetsList();  
    this.creatSmileswiper();  
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
    this.setData({
      page:1,
      assetsList:[]
    });
    this.creatAssetsList();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    // // 上拉加载
    if (this.data.shanglaBull) {
      let page = this.data.page;
      page++;
      this.setData({
        page: page
      })
      this.creatAssetsList();
    } else {

    }
  },

  // cui 创造照片详情数据
  creatGoods(){
    goodsDetail({
      id: 1
    },res => {
      console.log(res);
      if(res.code == 0){
        this.setData({
          goodsDate: res.data
        })
      }
    })
    var options = this.data.options;
    var requestUrl = "Goods/goodsDetails";
    var requestData = {
      id: this.data.options.id,
      token: this.data.mydata.token,
      ids: this.data.options.ids,
    }
    if (options.jumpType && options.jumpType == "cangpin"){
      requestUrl = "Collection/collectDetails";
      requestData = {
        token: this.data.mydata.token,
        id: this.data.options.id
      }
    } else if (options.jumpType && options.jumpType == "lunbotu"){
      requestUrl = "Banner/Theme?type=2";
      requestData = {
        token: this.data.mydata.token,
        type: 2,
        theme_no: this.data.options.theme_no
      }
    } else if (options.jumpType && options.jumpType == "dazhe"){
      requestUrl = "Mygoods/discountDetails";
      requestData = {
        token: this.data.mydata.token,
        id:this.data.options.id,
        discount: this.data.options.discount
      }
    }
    wx.showLoading({
      title: "加载中",
      mask: true
    });
    wx.request({
      url: url + requestUrl,
      data: requestData,
      success:(res)=>{
        console.log(res);        
        if(res.data.code == 0){
          // 判断作者风格类型
          if (res.data.data.goods.type == 1){
            res.data.data.goods.type = "自然";
          } else if (res.data.data.goods.type == 2){
            res.data.data.goods.type = "人文"
          } else if (res.data.data.goods.type == 3) {
            res.data.data.goods.type = "时尚"
          } else if (res.data.data.goods.type == 4) {
            res.data.data.goods.type = "当代"
          }
          // 判断头像是否是upload还是http
          if (res.data.data.goods.img.indexOf("uploads")>=0){
            this.setData({
              imgBull:true
            })
          }else{
            this.setData({
              imgBull: false
            })
          }
          
       
          // 简介 把时间戳转换成时间
          let time = app.TimeNohours(res.data.data.goods.film_time);
          res.data.data.goods.film_time = time;
          if (res.data.data.goods.content) {
            var article = res.data.data.goods.content;
            console.log(res.data.data.goods)
            // res.data.data.goods.article = WxParse.wxParse('article', 'html', article , this, 5);
          }
          this.setData({
            photo:res.data.data.photo,
            imgData:res.data.data.goods,
            allData:res.data.data,
          });
          
        }else{
          wx.showToast({
            title: res.data.msg,
            icon:"none",
            mask:true
          })
        }
        wx.hideLoading();
      }
    })
  },
  // 创造数据  评论列表
  creatAssetsList(){
    wx.request({
      url: url + 'Goods/evaluation',
      data:{
        token:this.data.mydata.token,
        id: this.data.options.id,
        page:this.data.page,
      },
      success:(res)=>{
        if(res.data.code == 0){
          //判断评论列表里面的头像是upload还是http
          if (res.data.data.data.length != 0){
            for (let i = 0; i < res.data.data.data.length;i++){
              if (res.data.data.data[i].img.indexOf("uploads") >= 0) {
                res.data.data.data[i].assetsimgBull = true;
              } else {
                res.data.data.data[i].assetsimgBull = false;
              };
              // 把时间戳 转换成为 时间 年月日时分秒
              res.data.data.data[i].addTime = app.dataTime(res.data.data.data[i].addTime)
            }

          }else{

          };
          var arr = this.data.assetsList;
          if (res.data.data.data.length < 10) {
            this.setData({
              shanglaBull: false
            })
          } else {
            this.setData({
              shanglaBull: true
            })
          }
          arr = arr.concat(res.data.data.data);
          this.setData({
            assetsList: arr,
          })
        }else{
          wx.showToast({
            title: res.data.msg,
            icon:"none",
            mask:true
          })
        }
      }
    })
  },
  // 创造数据  轮播图
  creatSmileswiper(){
    wx.request({
      url: url + 'Goods/rolling',
      data:{
        token:this.data.mydata.token,
        id:this.data.options.id
      },
      success:(res)=>{
        if(res.data.code == 0){
          var arrdata = res.data.data;
          if (arrdata.length>0){
            for (let i = 0; i < arrdata.length;i++){
              // 判断头像是否是upload还是http
              if (arrdata[i].img.indexOf("uploads") >= 0) {
                arrdata[i].imgBull = true
              } else {
                arrdata[i].imgBull = false
              }
              // 把时间戳转换为时间
              arrdata[i].time = app.TimeNohours(arrdata[i].payment_time);
              // 判断版型
              if (arrdata[i].goods_version == 1){
                arrdata[i].goods_version = "收藏版";
              } else if (arrdata[i].goods_version == 2){
                arrdata[i].goods_version = "精品版";
              } else if (arrdata[i].goods_version == 3) {
                arrdata[i].goods_version = "极品版";
              } else if (arrdata[i].goods_version == 4) {
                arrdata[i].goods_version = "博物馆版";
              }
              // 把名字只保留一位，其他替换成＊
              if (arrdata[i].account){
                arrdata[i].account = arrdata[i].account.substr(0, 1) + '***'
              }
            }
          }
          this.setData({
            swiperArr: arrdata
          })
        }else{
          console.log(res.data.msg)
        }
      }
    })
  },
  select(e){
    this.setData({
      swiperIndex:e.currentTarget.dataset.index,
    })
  },
  bindChange(e) {
    this.setData({ swiperIndex: e.detail.current });
  },
  // 点击加入购藏夹/购买
  joinShopping(e){
    //加载动画
    wx.showLoading({
      title: "加载中",
      mask: true
    });
    this.setData({
      buttonType:e.currentTarget.dataset.type,
    });
    wx.request({
      url: url + 'Order/attribute',
      data:{
        token: this.data.mydata.token,
        goods_id: this.data.options.id,
        discount: this.data.options.jumpType && ((this.data.options.jumpType == "lunbotu") || this.data.options.jumpType == "dazhe") ? (this.data.options.jumpType == "lunbotu"?this.data.imgData.discount:this.data.options.discount):"",
        theme_id: this.data.options.jumpType && this.data.options.jumpType == "lunbotu" ? this.data.options.theme_id : ""
      },
      success:(res)=>{
        console.log(res);
        wx.hideLoading();        
        if(res.data.code == 0){
          let content = res.data.data;
          this.setData({
            alertBull: true, 
            Format: content.goodsDate.version,
            selectFormat: content.goodsDate.version[0],
            selectDY: content.goodsDate.version[0] == "收藏版"?"不打印":"打印",   
            dayinMoneyData: content.print_cost,
            frameArr: content.frame,
            paperArr: content.paper,
            goodsDate: content.goodsDate,
            banshiMoney: content.goodsDate.price,
            price: content.goodsDate.version[0] == "收藏版" ? content.goodsDate.price : Number(content.goodsDate.price) + Number(content.print_cost)
          })
        }else if(res.data.code == 1140){
          // 如果没有绑定手机号，跳转到绑定手机号
          wx.navigateTo({
            url: '/pages/bindTel/bindTel',
          });
        }else{
          wx.showToast({
            title: res.data.msg,
            icon: "none",
            mask: true,
          })
        }
      }
    })
  },
  //选择版式
  selectFormat(e){
    let data = e.currentTarget.dataset.value;
    let goods = this.data.goodsDate;
    if (data == '收藏版'){
      this.setData({
        selectDY: "不打印",
        selectFormat: data,
        dayinMoney:0,
        kazhiMoney:0,
        xiangkuangMoney:0,
        allNum:1,
        banshiMoney: goods.collect_price,
        kazhiId:"",
        xiangkuangId:"",
      });
      this.price();
    // 判断是否为出售状态
    }else if(data == '精品版'){
      if (goods.quality_state == 2){
        wx.showToast({
          title: '此版本暂不出售',
          icon:"none",
          mask:true,
          duration:1000
        })
        return;
      }else{
        this.setData({
          selectFormat: data,
          selectCardboard: "",
          selectPhotoframe: '',
          selectDY: "打印",
          dayinMoney: this.data.dayinMoneyData,
          kazhiMoney: 0,
          xiangkuangMoney: 0,
          allNum: 1,
          banshiMoney: goods.quality_price,
          kazhiId: "",
          xiangkuangId: "",
        });
        this.price();
      }
    } else if (data == '极品版') {
      if (goods.best_state == 2) {
        wx.showToast({
          title: '此版本暂不出售',
          icon: "none",
          mask: true,
          duration: 1000
        })
        return;
      } else {
        this.setData({
          selectFormat: data,
          selectCardboard: "",
          selectPhotoframe: '',
          selectDY: "打印",
          allNum: 1,
          dayinMoney: this.data.dayinMoneyData,
          kazhiMoney: 0,
          xiangkuangMoney: 0,
          banshiMoney: goods.best_price,
          kazhiId: "",
          xiangkuangId: "",
        });
        this.price();
      }
    } else if (data == '博物馆版') {
      if (goods.museum_state == 2) {
        wx.showToast({
          title: '此版本暂不出售',
          icon: "none",
          mask: true,
          duration: 1000
        })
        return;
      } else {
        this.setData({
          selectFormat: data,
          selectCardboard: "",
          selectPhotoframe: '',
          selectDY: "打印",
          allNum: 1,
          dayinMoney: this.data.dayinMoneyData,
          kazhiMoney: 0,
          xiangkuangMoney: 0,
          banshiMoney: goods.museum_price,
          kazhiId: "",
          xiangkuangId: "",
        });
        this.price();
      }
    }
    
  },
// 判断选择的事打印 还是不打印
  selectIsDY(e){
    if (e.currentTarget.dataset.type == '打印'){
      // 如果是打印，价格变成接口传过来的打印费
      // console.log("dayin",this.data.banshiMoney)
      this.setData({
        dayinMoney: this.data.dayinMoneyData,
        selectDY: e.currentTarget.dataset.type,
      });
      this.price();
    }else{
      // 如果是不打印，打印费变成0
      this.setData({
        dayinMoney: 0,
        selectDY: e.currentTarget.dataset.type,
        selectCardboard: "",
        selectPhotoframe: '',
        kazhiMoney: 0,
        xiangkuangMoney: 0,
        kazhiId: "",
        xiangkuangId: "",
      });
      this.price();
    };
      
  },
//选择卡纸
  selectCardboard(e){
    let selPrice = Number(e.currentTarget.dataset.price);
    let selId = e.currentTarget.dataset.id; //选择的卡纸id
    let data = e.currentTarget.dataset.type;//选择的卡纸名字
    let goods = this.data.goodsDate;
    if (this.data.selectFormat != '收藏版'){
      data = '';
      selId = '';
      selPrice = 0;
    }else{
      // 如果选择的是收藏版＋不打印
      if (this.data.selectDY == '不打印'){
        data = '';
        selId = '';
        selPrice = 0;
      }else{
        // 如果选择的是收藏版＋打印，表示可以选择卡纸和相框

      }
    }
    this.setData({
      selectCardboard: data,
      kazhiMoney: selPrice,
      kazhiId: selId
      
    });
    this.price();
  },
// 选择相框
  selectPhotoframe(e){
    let selId = e.currentTarget.dataset.id; //选择的相框id
    let selPrice = Number(e.currentTarget.dataset.price);
    let data = e.currentTarget.dataset.type;
    if (this.data.selectFormat != '收藏版') {
      data = '';
      selId='';
      selPrice = 0;
    } else {
      if (this.data.selectDY == '不打印') {
        data = '';
        selId = '';
        selPrice = 0;
      }
    }
    this.setData({
      selectPhotoframe: data,
      xiangkuangMoney: selPrice,
      xiangkuangId: selId
    });
    this.price();
  },
  // 加减 数量
  add(){
    let num = this.data.allNum;
    let goodsDate = this.data.goodsDate;
    if (this.data.selectFormat == "收藏版"){
      if (this.data.selectDY == "不打印") {
        if (num < goodsDate.collect_versions) {
          if (num < 30){
            num++;
            this.setData({
              allNum: num
            });
            this.price();
          }else{
            wx.showToast({
              title: '每单限购藏30个版数',
              icon: "none",
              mask: true,
            });
            return;
          }
        } else {
          wx.showToast({
            title: '数量超过剩余版数',
            icon: "none",
            mask: true,
          });
          return;
        }
      } else {
        // 打印状态下
        if ( num < goodsDate.collect_versions){
          if (num < 30) {
            num++;
            this.setData({
              allNum: num
            });
            this.price();
          } else {
            wx.showToast({
              title: '每单限购藏30个版数',
              icon: "none",
              mask: true,
            });
            return;
          }
          
        }else{
          wx.showToast({
            title: '数量超过剩余版数',
            icon:'none',
            mask: true
          });
          return;
        }
      }
    } else if (this.data.selectFormat == "精品版"){
        if (this.data.selectDY == "不打印") {
          // 剩余版数
          if (num < goodsDate.quality_versions) {
            num  = 1;
            wx.showToast({
              title: '非收藏版只能购买一个',
              icon: 'none',
              mask:true
            });
            this.setData({
              allNum: num
            });
            this.price();
          } else {
            wx.showToast({
              title: '数量超过剩余版数',
              icon: "none",
              mask: true
            });
            return;
          }
        } else {
          // 打印状态下
          // 判断选择的数量是否同时小于剩余版式和剩余库存
          if ( num < goodsDate.quality_versions) {
            num = 1;
            wx.showToast({
              title: '非收藏版只能购买一个',
              icon: 'none',
              mask: true
            });
            this.setData({
              allNum: num
            });
            this.price();
          } else {
            wx.showToast({
              title: '数量超过剩余库存',
              icon: 'none',
              mask: true
            });
            return;
          }
        }
    } else if (this.data.selectFormat == "极品版") {
      if (this.data.selectDY == "不打印") {
        // 剩余版数
        if (num < goodsDate.best_versions) {
          num = 1;
          wx.showToast({
            title: '非收藏版只能购买一个',
            icon: 'none',
            mask: true
          });
          this.setData({
            allNum: num
          });
          this.price();
        } else {
          wx.showToast({
            title: '数量超过剩余版数',
            icon: "none",
            mask: true
          });
          return;
        }
      } else {
        // 打印状态下
        // 判断选择的数量是否同时小于剩余版式和库存
        if ( num < goodsDate.best_versions) {
          num = 1;
          wx.showToast({
            title: '非收藏版只能购买一个',
            icon: 'none',
            mask: true
          });
          this.setData({
            allNum: num
          });
          this.price();
        } else {
          wx.showToast({
            title: '数量超过剩余库存',
            icon: 'none',
            mask: true
          });
          return;
        }
      }
    } else if (this.data.selectFormat == "博物馆版") {
      if (this.data.selectDY == "不打印") {
        // 剩余版数
        if (num < goodsDate.museum_versions) {
          num = 1;
          wx.showToast({
            title: '非收藏版只能购买一个',
            icon: 'none',
            mask: true
          });
          this.setData({
            allNum: num
          });
          this.price();
        } else {
          wx.showToast({
            title: '数量超过剩余版数',
            icon: "none",
            mask: true
          });
          return;
        }
      } else {
        // 打印状态下
        // 判断选择的数量是否同时小于剩余版式和库存
        if ( num < goodsDate.museum_versions) {
          num = 1;
          wx.showToast({
            title: '非收藏版只能购买一个',
            icon: 'none',
            mask: true
          });
          this.setData({
            allNum: num
          });
          this.price();
        } else {
          wx.showToast({
            title: '数量超过剩余库存',
            icon: 'none',
            mask: true
          });
          return;
        }
      }
    }
  },
  reduce(){
    let num = this.data.allNum;
    let goodsDate = this.data.goodsDate;
    num --;
    if(num <= 1){
      num = 1;
    };
    this.setData({
      allNum:num
    });
    this.price();
  }, 
  // 计算价格
  price(){
    let price = 0;
    price = ((Number(this.data.banshiMoney) + Number(this.data.dayinMoney) + Number(this.data.kazhiMoney) + Number(this.data.xiangkuangMoney)) * Number(this.data.allNum)).toFixed(2);
    this.setData({
      price:price,
    })
  },
  // 加入购物车／立即购藏
  sumbit(){
    let num = this.data.allNum;
    let goodsDate = this.data.goodsDate;
    let selType = this.data.selectFormat;
    let buttonType = this.data.buttonType;
    // if (this.data.goodsDate.is_shelves == 1){
    //   if (this.data.goodsDate.is_delete == 2){
    //     if (this.data.goodsDate.is_receive == 2){
          if (this.data.selectFormat == "收藏版") {
            if (this.data.selectDY == "不打印") {
              // 不打印状态下 , 如果是从轮播图进来的 不需要判断版数
              if (this.data.options.jumpType == "lunbotu") {
                // 如果点击的是加入购藏夹 确定按钮，调用joinData函数，如果点击的事立即购藏的确定按钮，调用sumbitBuy函数
                if (buttonType == "shop") {
                  this.joinData();
                } else if (buttonType == "buy") {
                  this.sumbitBuy();
                }
              }else{
                if (num <= goodsDate.collect_versions) {
                  // 如果点击的是加入购藏夹 确定按钮，调用joinData函数，如果点击的事立即购藏的确定按钮，调用sumbitBuy函数
                  if (buttonType == "shop"){
                    this.joinData();                  
                  } else if (buttonType == "buy"){
                    this.sumbitBuy();
                  }
                  // 调用加入购物车接口
                } else {
                  wx.showToast({
                    title: '数量超过剩余版数',
                    icon: "none",
                    mask: true,
                  });
                  return;
                }
              }
              
            } else {
              // 打印状态下 , 如果是从轮播图进来的 不需要判断版数
              if(this.data.options.jumpType == "lunbotu"){
                // 如果点击的是加入购藏夹 确定按钮，调用joinData函数，如果点击的事立即购藏的确定按钮，调用sumbitBuy函数
                if (buttonType == "shop") {
                  this.joinData();
                } else if (buttonType == "buy") {
                  this.sumbitBuy();
                }
              }else{

                if ( num <= goodsDate.collect_versions) {
                  // 如果点击的是加入购藏夹 确定按钮，调用joinData函数，如果点击的事立即购藏的确定按钮，调用sumbitBuy函数
                  if (buttonType == "shop") {
                    this.joinData();
                  } else if (buttonType == "buy") {
                    this.sumbitBuy();
                  }

                } else {
                  wx.showToast({
                    title: '数量超过剩余库存',
                    icon: 'none',
                    mask: true
                  });
                  return;
                }
              }
              
            }
          } else if (this.data.selectFormat == "精品版") {
            if (this.data.selectDY == "不打印") {
              // 剩余版数
              if (num <= goodsDate.quality_versions) {
                // 如果点击的是加入购藏夹 确定按钮，调用joinData函数，如果点击的事立即购藏的确定按钮，调用sumbitBuy函数
                if (buttonType == "shop") {
                  this.joinData();
                } else if (buttonType == "buy") {
                  this.sumbitBuy();
                }


              } else {
                wx.showToast({
                  title: '数量超过剩余版数',
                  icon: "none",
                  mask: true
                });
                return;
              }
            } else {
              // 打印状态下
              // 判断选择的数量是否同时小于剩余版式和剩余库存
              if ( num <= goodsDate.quality_versions) {
                // 如果点击的是加入购藏夹 确定按钮，调用joinData函数，如果点击的事立即购藏的确定按钮，调用sumbitBuy函数
                if (buttonType == "shop") {
                  this.joinData();
                } else if (buttonType == "buy") {
                  this.sumbitBuy();
                }


              } else {
                wx.showToast({
                  title: '数量超过剩余库存',
                  icon: 'none',
                  mask: true
                });
                return;
              }
            }
          } else if (this.data.selectFormat == "极品版") {
            if (this.data.selectDY == "不打印") {
              // 剩余版数
              if (num <= goodsDate.best_versions) {
                // 如果点击的是加入购藏夹 确定按钮，调用joinData函数，如果点击的事立即购藏的确定按钮，调用sumbitBuy函数
                if (buttonType == "shop") {
                  this.joinData();
                } else if (buttonType == "buy") {
                  this.sumbitBuy();
                }


              } else {
                wx.showToast({
                  title: '数量超过剩余版数',
                  icon: "none",
                  mask: true
                });
                return;
              }
            } else {
              // 打印状态下
              // 判断选择的数量是否同时小于剩余版式和库存
              if ( num <= goodsDate.best_versions) {
                // 如果点击的是加入购藏夹 确定按钮，调用joinData函数，如果点击的事立即购藏的确定按钮，调用sumbitBuy函数
                if (buttonType == "shop") {
                  this.joinData();
                } else if (buttonType == "buy") {
                  this.sumbitBuy();
                }


              } else {
                wx.showToast({
                  title: '数量超过剩余库存',
                  icon: 'none',
                  mask: true
                });
                return;
              }
            }
          } else if (this.data.selectFormat == "博物馆版") {
            if (this.data.selectDY == "不打印") {
              // 剩余版数
              if (num <= goodsDate.museum_versions) {
                // 如果点击的是加入购藏夹 确定按钮，调用joinData函数，如果点击的事立即购藏的确定按钮，调用sumbitBuy函数
                if (buttonType == "shop") {
                  this.joinData();
                } else if (buttonType == "buy") {
                  this.sumbitBuy();
                }


              } else {
                wx.showToast({
                  title: '数量超过剩余版数',
                  icon: "none",
                  mask: true
                });
                return;
              }
            } else {
              // 打印状态下
              // 判断选择的数量是否同时小于剩余版式和库存
              if ( num <= goodsDate.museum_versions) {
                // 如果点击的是加入购藏夹 确定按钮，调用joinData函数，如果点击的事立即购藏的确定按钮，调用sumbitBuy函数
                if (buttonType == "shop") {
                  this.joinData();
                } else if (buttonType == "buy") {
                  this.sumbitBuy();
                }

              } else {
                wx.showToast({
                  title: '数量超过剩余库存',
                  icon: 'none',
                  mask: true
                });
                return;
              }
            }
          }
    //     }else{
    //       wx.showToast({
    //         title: '您所选择的商品已经被赠送',
    //         icon: "none",
    //         mask: true
    //       });
    //       return;
    //     }
    //   }else{
    //     wx.showToast({
    //       title: '您所选择的商品已经被删除',
    //       icon: "none",
    //       mask: true
    //     });
    //     return;
    //   }
    // }else{
    //   wx.showToast({
    //     title: '您所选择的商品已经下架',
    //     icon:"none",
    //     mask:true
    //   });
    //   return;
    // }
  },
  // 点击立即购藏确定的函数
  sumbitBuy(){
    let is_print = this.data.selectDY;
    let version = this.data.selectFormat;
    console.log(this.data.options);
    var requestData = {};
    if (is_print == "打印") {
      is_print = 1;
    } else {
      is_print = 2;
    }
    if (version == "收藏版") {
      version = 1;
    } else if (version == "精品版") {
      version = 2;
    } else if (version == "极品版") {
      version = 3;
    } else if (version == "博物馆版") {
      version = 4;
    }
    if (this.data.options.jumpType == "lunbotu"){
      requestData = {
        token: this.data.mydata.token,
        goods_id: this.data.options.id,
        goods_num: this.data.allNum,
        version: version,
        paper_id: this.data.kazhiId,
        frame_id: this.data.xiangkuangId,
        is_print: is_print,
        discount: this.data.allData.theme.discount,
        theme_id: this.data.allData.theme_id 
      }
    } else if (this.data.options.jumpType == "dazhe"){
      requestData = {
        token: this.data.mydata.token,
        goods_id: this.data.options.id,
        goods_num: this.data.allNum,
        version: version,
        paper_id: this.data.kazhiId,
        frame_id: this.data.xiangkuangId,
        is_print: is_print,
        discount: this.data.options.discount,
      }
    }else{
      requestData = {
        token: this.data.mydata.token,
        goods_id: this.data.options.id,
        goods_num: this.data.allNum,
        version: version,
        paper_id: this.data.kazhiId,
        frame_id: this.data.xiangkuangId,
        is_print: is_print,
      }
    }
    wx.request({
      url: url + 'Order/logistics',
      // method:"POST",
      data: requestData,
      success:(res)=>{
        console.log(res);
        if(res.data.code == 0){
          wx.setStorageSync("detail", res.data.data);
          wx.navigateTo({
            url:"../sureOrder/sureOrder?type=1"
          })
        }else{
          wx.showToast({
            title: res.data.msg,
            icon:"none"
          })
        }
      }
    })
  },
  // 点击加入收藏夹确定的时候需要走的函数
  joinData(){
    let is_print = this.data.selectDY;
    let version = this.data.selectFormat;
    if (is_print == "打印"){
      is_print = 1;
    }else{
      is_print = 2;
    }
    if (version == "收藏版"){
      version = 1;
    } else if (version == "精品版"){
      version = 2;
    } else if (version == "极品版") {
      version = 3;
    } else if (version == "博物馆版") {
      version = 4;
    }
    wx.request({
      url: url + 'Shopping/add',
      method:"POST",
      data:{
        token:this.data.mydata.token,
        goods_id: this.data.options.id,
        goods_name: this.data.goodsDate.goods_name,
        num:this.data.allNum,
        version: version,
        is_print: is_print,
        current_price:this.data.price,
        paper_id:this.data.kazhiId,
        frame_id:this.data.xiangkuangId,
        paper: this.data.selectCardboard,
        frame: this.data.selectPhotoframe
      },
      success:(res)=>{
        if(res.data.code == 0){
          wx.showToast({
            title: '加入成功',
            icon:"success",
            duration:2000,
            mask:true
          });
          let a1 = setTimeout(()=>{
            this.close();
            this.setData({
              tusiState:1
            })
            clearTimeout(a1);
          },2500) 
        }else{
          wx.showToast({
            title: res.data.msg,
            icon:"none",
            mask:true
          })
        }
      }
    })
  },
  // 关闭弹窗
  close(){
    // 初始化数据
    this.setData({
      alertBull: false,
      selectCardboard: "",//选择的卡纸
      selectPhotoframe: "",//选择的相框
      selectDY: "不打印",//选择打印或者不打印
      selectFormat: "收藏版",//选择的版式
      allNum:1,//数量
      banshiMoney: 0,//产生的版式价格
      dayinMoney: 0,//产生的打印费
      kazhiMoney: 0,//产生的卡纸费用
      xiangkuangMoney: 0,//产生的相框费用
    })
  },
  // ********* 点击图片查看大图 ***********
  seeBig(){
    //加载动画
    wx.showLoading({
      title: "加载中",
      mask: true
    });
    wx.request({
      url: url + 'Goods/sliding',
      method: "POST",      
      data:{
        token:this.data.mydata.token,
        ids:this.data.options.ids
      },
      success:(res)=>{
        wx.hideLoading();        
        console.log(res);
        if(res.data.code == 0){
          var id = this.data.options.id;
          var data = res.data.data.data;
          if(res.data.data.magnify == 2){
            wx.showToast({
              title: '左右滑动可切换组图中的其他作品，上下滑动可换下个单张(或组图)其他作品',
              duration:5000,
              icon:"none"
            })
          }
          for (let i = 0; i < data.length; i++) {
            if (id == data[i].id) {
              this.setData({
                swiperCurrent: i,
              });
              break;
            }
          }
          this.setData({
            photoData: res.data.data.data,
            bigBull: true,
          })
        }else{
          wx.showToast({
            title: res.data.msg,
            icon:"none",
            mask: true,
          })
        }
      }
    })
  },
  // 关闭大图
  closeBig(){
   
    var id = this.data.photoData[this.data.wantIndex].id;
    this.data.options.id = id;
    this.setData({
      bigBull: false,
    })
    this.creatGoods();
    this.creatAssetsList();
    this.creatSmileswiper();  
  },
  // 上下滑动的时候增加喜欢
  addLove(e){
    //加载动画
    wx.showLoading({
      title: "加载中",
      mask: true
    });
    var index = e.detail.current;
    var photo = this.data.photoData;
    console.log(photo);
    wx.request({
      url:url + 'Goods/like',
      data:{
        token:this.data.mydata.token,
        id: photo[index].id
      },
      success:(res)=>{
        wx.hideLoading();        
        if(res.data.code == 0){
          console.log("增加一次喜欢");
          this.setData({
            // 当前查看大图所处在的轮播图位置
            wantIndex: index
          })
        }else{
          //弹出框
          wx.showToast({
            title: res.data.msg,
            icon: "none",
            mask: true,
            duration: 1500
          })
        }
      }
    })
  },

  load: function (e) {
    // console.log(e);
    // bindload 这个api是<image>组件的api类似<img>的onload属性 
    this.setData({
        'touch.baseWidth': e.detail.width,
        'touch.baseHeight': e.detail.height,
        // 'touch.scaleWidth': e.detail.width,
        // 'touch.scaleHeight': e.detail.height
    });
  },
  stopClick(e){
    var src = e.currentTarget.dataset.src;
    wx.previewImage({
      current: src, // 当前显示图片的http链接
      urls: [src] // 需要预览的图片http链接列表
    })
  },
// ************手势代码结束

//从我的作品进来代码开始  ＊＊＊＊＊赠送和打折
  clickZengsong(){
    var content = this.data.imgData;
    if (content.collect_issue <= 0){
      wx.showToast({
        title: '此作品无法被赠送',
        icon:"none",
        mask:true
      })
      return;
    }
    if (content.goods_structure == 1){
      // 如果是单张被赠送
      // console.log(this.data.imgData);
      // wx.request({
      //   url: url + 'Mygoods/versionNo',
      //   method: "POST",
      //   data: {
      //     token: this.data.mydata.token,
      //     goods_id: this.data.options.id,
      //     photo_no: this.data.imgData.goods_no,
      //   },
      //   success:(res)=>{
      //     console.log(res);
      //     if(res.data.code == 0){
      //       console.log("可以被赠送")
      //     }else{
      //       console.log("无法被赠送")
      //       wx.showToast({
      //         title: res.data.msg,
      //         icon:"none",
      //         mask:true
      //       });
      //     }
      //   }
      // })
    } else if (content.goods_structure == 2){
      // 如果是组图被赠送
      wx.navigateTo({
        url: "/pages/my/myWorks/worksList/worksList?id=" + this.data.options.id,
      })
    }
  },
  // 价格详情
  priceDetail(e){
    this.setData({
      priceDetailBull:true
    })
    wx.request({
      url: url + 'Mygoods/getPrice',
      data:{
        token:this.data.mydata.token,
        id: this.data.options.id
      },
      success:(res)=>{
        console.log(res);
        if(res.data.code == 0){
          this.setData({
            priceDetail:res.data.data
          })
        }else{
          wx.showToast({
            title: res.data.msg,
            icon:"none",
            mask:true
          })
        }
      }
    })
  },
  // 关闭价格详情
  closepriceDetail(){
    this.setData({
      priceDetailBull:false
    })
  },
  // 打折代码
  // 点击打折
  discount(){
    // console.log(this.data.imgData);
    if (this.data.imgData.collect_issue == 0){
      wx.showToast({
        title: '此作品无法打折出售',
        icon:"none",
        mask:true,
        duration:1000
      })
      return;
    }
    this.setData({
      dazheBull:true,
      discountPrice: this.data.imgData.collect_price
    })
  },
  // 点击打折关闭按钮
  discountClose(){
    this.setData({
      dazheBull: false,
      selectDiscount:"",
      seldiscount:""
    })
  },
  // 选择打折标签
  selectDiscount(e){
    console.log(e);
    var price = this.data.imgData.collect_price;
    var discount = e.currentTarget.dataset.discount;
    var seldiscount = e.currentTarget.dataset.seldiscount;
    console.log(typeof (price), typeof (discount));
    var discountPrice = Math.round(Number(price) * Number(discount));
    this.setData({
      selectDiscount:e.currentTarget.dataset.name,
      discountPrice: discountPrice,
      seldiscount: seldiscount
    })
  },
  // 如果没有选择折扣，进行提醒
  discount_Notic(){
    wx.showToast({
      title: '请选择折扣',
      icon:"none",
      mask:true
    })
  },
  // 打折代码结束
//＊＊＊＊＊＊从我的作品进来代码结束 
  // 跳转页面
  jumpHome(){
    wx.switchTab({
      url: '/pages/home/home'
    })
  },
  jumpShopping(){
    wx.navigateTo({
      url: '/pages/shopping/shopping',
    })
  },
  // 我的藏品－详情－查看版式－版式版号
  jumpHaveformat(e){
    var id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url:"/pages/haveFormat/haveFormat?id="+id
    })
  },
  // 跳转到摄影师详情
  jumpPhotographer(e){
    let id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '/pages/user-detail/user-detail?id=' + 1,
    })
  },
  onShareAppMessage(options) {
    console.log(options);
    if (options.from == 'menu'){
      return {
        title: "2019，我的影像价值观",
        path:"/pages/home/home"
      }
    }
    
    if (options.from == 'button' && options.target.dataset.name == "fenxiang"){
      console.log(this.data.options.id);
      console.log(this.data.options.ids);
      console.log(this.data.seldiscount);
      return {
        title: "作者愿意" + this.data.selectDiscount + "出售" + this.data.imgData.goods_name + 
        "。此链接的折扣价格仅24小时内有效。",
        imageUrl: this.data.imgUrl + this.data.photo[0].img,
        // imageUrl:"/images/home/logo2x.png",
        path: "/pages/home/home?id=" + this.data.options.id + "&ids=" + this.data.options.ids + "&shareType=dazhe" + "&discount=" + this.data.seldiscount,
        // query: "id=" + this.data.options.id + "&ids=" + this.data.options.ids + "shareType=dazhe" + "&discount=" + this.data.seldiscount,
      }
    } else if (options.from == 'button' && options.target.dataset.name == "zengsong"){
      var tmp = Date.parse(new Date()).toString();
      tmp = tmp.substr(0, 10);
      console.log(this.data.options.id);
      console.log(this.data.mydata.unionId);
      console.log(tmp);
      return {
        title: "好友向您赠送了一幅收藏版作品，此链接仅24小时内有效，请及时点击接收。",
        imageUrl: this.data.imgUrl + this.data.photo[0].img,
        path: "/pages/home/home?goods_id=" + this.data.options.id + "&unionId=" + this.data.mydata.unionId + "&addTime=" + tmp + "&shareType=zengsongzuopin",
        // query: "&goods_id=" + this.data.options.id + "&unionId=" + this.data.mydata.unionId + "&addTime=" + tmp + "&shareType=zengsongzuopin",
      
      }
    }
  }
})