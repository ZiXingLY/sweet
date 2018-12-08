function forTime(list, addtime, long){
  // 如果是10位数 long为真 除以1000
  //获取本地时间戳
  let nowTime = Date.parse(new Date());
  if (long) {
    nowTime = nowTime / 1000;
  }

  for (let i = 0; i < list.length; i++) {
    let timeDifference = nowTime - list[i][addtime];
    if(!long){
      timeDifference = timeDifference / 1000;
    }
    //计算天数
    list[i].difference = Math.round(timeDifference / (24 * 3600));

    //计算出小时数
    let remainingTime = timeDifference % (24 * 3600);
    //计算天数后剩余的小时数
    list[i].hours = Math.floor(remainingTime / 3600);
    //计算相差分钟数
    let remainingMinute = remainingTime % 3600;
    //计算小时数后剩余的分钟数
    list[i].minutes = Math.floor(remainingMinute / 60);

    //计算距离现在的时间
    if (list[i].difference > 0) {
      if (list[i].difference == 1) {
        list[i].showTime = '昨天 ' + GetDateD(list[i][addtime], long);
      } else {
        list[i].showTime = GetDateT(list[i][addtime], long);
      }
    } else {
      if (list[i].hours > 0) {
        list[i].showTime = '今天 ' + GetDateD(list[i][addtime], long);
      } else {
        if (list[i].minutes < 2) {
          list[i].showTime = '刚刚';
        } else {
          list[i].showTime = list[i].minutes + '分钟前';
        }
      }
    }
  }
  return list;
}
// 获取日期
function GetDateT(ns, long) {
  if (!long) {
    ns = ns / 1000;
  }
  let d, s;
  d = new Date(ns * 1000);
  s = d.getFullYear() + "-"; //取年份
  s = s + (d.getMonth() + 1) + "-";//取月份
  s += d.getDate() + " "; //取日期

  return (s);
}
//获取时间
function GetDateD(ns, long) {
  if (!long){
    ns = ns/1000;
  }
  let d, s;
  console.log(ns);  
  d = new Date(ns * 1000);
  console.log(d);
  s = d.getHours() + ":";       //取小时
  if (d.getMinutes() < 10) {
    s += '0' + d.getMinutes();    //取分
  } else {
    s += d.getMinutes();    //取分
  }
  return (s);
}

function timestampToTime(timestamp) {
  let date = new Date(timestamp);
  let Y, M, D, h, m;
  Y = date.getFullYear() + '.';
  M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '.';
  D = date.getDate() + ' ';
  h = (date.getHours() >= 10 ? date.getHours() : '0' + date.getHours()) + ':';
  m = date.getMinutes() >= 10 ? date.getMinutes() : '0' + date.getMinutes();
  return Y + M + D + h + m;
}

module.exports = {
  forTime: forTime,
  timestampToTime: timestampToTime
}