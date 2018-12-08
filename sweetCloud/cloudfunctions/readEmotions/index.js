// 云函数入口文件
const cloud = require('wx-server-sdk')

cloud.init()

// 云函数入口函数
const db = cloud.database()
exports.main = async (event, context) => {
  let start = event.start ? event.start : 0;
  try {
    return await db.collection('emotions')
      .orderBy('createTime', 'desc')
      .skip(start)
      .limit(10)
      .get()
  } catch (e) {
    console.error(e)
  }
}
// exports.main = async (event, context) => {
//   let _openid = event.userInfo.openId;

// }