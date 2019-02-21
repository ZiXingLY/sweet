//const ROOT_URL = "https://51cnews.knighteam.com/";
// const ROOT_URL = "https://qstitvc.com/";
const ROOT_URL = "https://localhost:8443/";
// const ROOT_URL = 'https://ttq.tiantianquan.xyz/'
// const ROOT_URL = "http://192.168.0.218:8080/";

// 分享图片
let shareImg = (params, success) => {
  _http({
    url: "setImage",
    data: params,
    success: (res) => {
      success(res);
    }
  })
}

// 分享数
let shareNum = (params, success) => {
  _http({
    url: "share/getShareNumber",
    data: params,
    success: (res) => {
      success(res.data);
    }
  })
}

// 充值记录
let vipRecord = (header, params, success) => {
  _http({
    url: "vip/pcList",
    header: header,
    data: params,
    success: (res) => {
      success(res.data);
    }
  })
}

// 查询用户信息
let checkUser = (params, success) => {
  _http({
    url: "user/WXRegister",
    data: params,
    method: "POST",
    success: (res) => {
      success(res.data);
    }
  })
}

// 轮播图
let getbanner = (success) => {
  _http({
    url: "getBanner",
    data: {},
    success: (res) => {
      success(res.data);
    }
  })
}

// 积分账单
let myVipList = (header, params, success) => {
  _http({
    url: "credits/list",
    data: params,
    header: header,
    success: (res) => {
      success(res.data);
    }
  })
}

// 会员积分
let myVip = (header, success) => {
  _http({
    url: "credits/getUserAllScore",
    header: header,
    success: (res) => {
      success(res.data);
    }
  })
}

// 我的发布
let myArticleList = (header, params, success) => {
  _http({
    url: "article/wxFindArticleByState",
    data: params,
    header: header,
    success: (res) => {
      success(res.data);
    }
  })
}

// 消息列表
let newsList = (header, params, success) => {
  _http({
    url: "message/list",
    data: params,
    header: header,
    success: (res) => {
      success(res.data);
    }
  })
}

// 是否登录
let isLogin = (header, success) => {
  _http({
    url: "user/isLogin",
    data: {},
    header: header,
    success: (res) => {
      success(res);
    }
  })
}

// 快讯详情
let flashNewsDetail = (params, success) => {
  _http({
    url: "flash/detail",
    data: params,
    success: (res) => {
      success(res);
    }
  })
}

// 快讯页面
let flashNews = (params, success) => {
  _http({
    url: "flash/getFlashList",
    data: params,
    success: (res) => {
      success(res);
    }
  })
}

// 快讯分享加积分
let addIntegralFlash = (params, success) => {
  _http({
    url: "credits/toReadFlashAddScoreByOpenid",
    data: params,
    success: (res) => {
      success(res);
    }
  })
}

// 文章分享加积分
let addIntegral = (params, success) => {
  _http({
    url: "credits/shareToAddByOpenid",
    data: params,
    success: (res) => {
      success(res);
    }
  })
}

// 判断文章是否点过赞
let checkZan = (header, params, success) => {
  _http({
    url: "liker/isLike",
    data: params,
    header: header,
    success: (res) => {
      success(res);
    }
  })
}

// 登录
let wxLogin = (params, success) => {
  _http({
    url: "user/wxLogin",
    data: params,
    method: "POST",
    success: (res) => {
      success(res);
    }
  })
}

// 绑定手机号
let bindAccount = (params, success) => {
  _http({
    url: "user/bindPhone",
    data: params,
    method: "POST",
    success: (res) => {
      success(res.data);
    }
  })
}

// 获取openid
let getOpenid = (params, success) => {
  _http({
    url: "user/getOpenid",
    data: params,
    success: (res) => {
      success(res.data);
    }
  })
}

// 获取用户信息
let getUserInfo = (params, success) => {
  _http({
    url: "user/wxUserInfo",
    data: params,
    method: "POST",
    success: (res) => {
      success(res.data);
    }
  })
}

// 获取验证码
let getCode = (params, success) => {
  _http({
    url: "sys/sms/sendSMS",
    data: params,
    success: (res) => {
      success(res.data);
    }
  })
}

// 验证验证码
let checkCode = (params, success) => {
  _http({
    url: "sys/sms/checkSMS",
    data: params,
    success: (res) => {
      success(res.data);
    }
  })
}

// 点赞
let goodThumbs = (header, params, success) => {
  _http({
    url: "liker/clickLike",
    data: params,
    header: header,
    success: (res) => {
      success(res);
    }
  })
}

// 文章详情
let articleDetail = (header, params, success) => {
  _http({
    url: "article/readArticle",
    data: params,
    header: header,
    success: (res) => {
      success(res.data);
    }
  })
}

// 获取资讯的所有分类
let getHomeNav = (success) => {
  _http({
    url: "category/getCategory",
    data: {},
    success: (res) => {
      success(res.data);
    }
  })
}

// 获取首页数据
let getHomeData = (params, success) => {
  _http({
    url: "article/getArticleByCategory",
    data: params,
    success: (res) => {
      success(res.data);
    }
  })
}

let getEmotions = (params, success) => {
  _http({
    url: "emotions/list",
    data: params,
    header: {
      'content-type': 'application/json', // 默认值
      'x-auth-token': wx.getStorageSync('token')
    },
    success: (res) => {
      success(res.data);
    }
  })
}
let saveEmotion = (header, params, success) => {
  _http({
    url: "emotions/add",
    data: params,
    header: header,
    method: "POST",
    success: (res) => {

      if (res.data.code == 403) {
        wx.showModal({
          title: '提示',
          content: '请先登录',
          success(res) {
            if (res.confirm) {
              console.log('用户点击确定')
            } else if (res.cancel) {
              console.log('用户点击取消')
            }
          }
        })
      }else{
        success(res.data);
      }
    }
  })
}

// 网络请求自动增加状态提示框
let _http = (r) => {
  wx.showLoading({
    title: '加载中...'
  });
  wx.request({
    url: ROOT_URL + r.url,
    method: r.method ? r.method : "GET",
    data: r.data,
    header: r.header ? r.header : {},
    success: (res) => {
      wx.hideLoading();
      if (r.success && res['data']) {
        r.success(res);
      } else {
        // wx.showToast({
        //   title: res['data']['msg'],
        //   icon: 'none'
        // })
        wx.showToast({
          title: '系统繁忙，请稍后重试',
          icon: "none"
        });
      }
    },
    fail: (res) => {
      wx.hideLoading();
      if (r.fail) {
        r.fail(res);
      } else {
        wx.showToast({
          title: '系统繁忙，请稍后重试',
          icon: "none"
        });
      }
    },
    complete: (res) => {
      if (r.complete) {
        r.complete(res);
      }
    }
  })
}


module.exports = {
  getHomeNav: getHomeNav,
  getHomeData: getHomeData,
  articleDetail: articleDetail,
  goodThumbs: goodThumbs,
  getCode: getCode,
  checkCode: checkCode,
  getUserInfo: getUserInfo,
  getOpenid: getOpenid,
  bindAccount: bindAccount,
  wxLogin: wxLogin,
  checkZan: checkZan,
  addIntegral: addIntegral,
  flashNews: flashNews,
  addIntegralFlash: addIntegralFlash,
  isLogin: isLogin,
  newsList: newsList,
  myArticleList: myArticleList,
  myVipList: myVipList,
  myVip: myVip,
  flashNewsDetail: flashNewsDetail,
  getbanner: getbanner,
  checkUser: checkUser,
  vipRecord: vipRecord,
  shareNum: shareNum,
  shareImg: shareImg,
  getEmotions: getEmotions,
  saveEmotion: saveEmotion
}