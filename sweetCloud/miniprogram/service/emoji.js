function strEmojiDiscode(str) {
  // 加入常用解析
  str = str.replace(/\[em\]e400026\[\/em\]/g, '<img src="http://qzonestyle.gtimg.cn/qzone/em/e400026.gif" title="祖国">');
  str = str.replace(/&quot;/g, "'");
  str = str.replace(/&amp;/g, '&');
  // str = str.replace(/&lt;/g, '‹');
  // str = str.replace(/&gt;/g, '›');

  str = str.replace(/&lt;/g, '<');
  str = str.replace(/&gt;/g, '>');
  str = str.replace(/&#8226;/g, '•');

  return str;
}

module.exports = {
  strEmojiDiscode: strEmojiDiscode
}