from selenium import webdriver
from selenium.webdriver.common.by import By
import time
import re
import json
import sched

# import pymysql
import pymongo
 
# schedule = sched.scheduler(time.time,time.sleep)

def get_emotion():
    # 安装PhantomJS指定路径
    driver = webdriver.PhantomJS(executable_path=r"/Users/anshi/Desktop/resource/phantomjs-2.1.1-macosx/bin/phantomjs")
    # driver = webdriver.PhantomJS(executable_path=r"/root/phantomjs-2.1.1-linux-x86_64/bin/phantomjs")
    # driver = webdriver.Chrome(executable_path=r'C:\Users\Administrator\AppData\Local\Programs\Python\Python36-32\Scripts\phantomjs-2.1.1-windows\phantomjs-2.1.1-windows\bin\chromedriver.exe') #这个是chormedriver的地址
    driver.get('https://qzone.qq.com/')
    print("抓取：https://qzone.qq.com/")

    driver.switch_to.frame('login_frame')
    driver.find_element_by_id('switcher_plogin').click()
    time.sleep(0.5)

    driver.find_element_by_id('u').clear()
    driver.find_element_by_id('u').send_keys('1926791261')  # 这里填写你的QQ号
    driver.find_element_by_id('p').clear()
    driver.find_element_by_id('p').send_keys('hanghang183367.')  # 这里填写你的QQ密码

    driver.find_element_by_id('login_button').click()
    print("登录")
    time.sleep(3)
    # f = open('/Users/anshi/Desktop/resource/anshi/QQ1.txt','w',encoding='utf-8')
    # f1 = open('/Users/anshi/Desktop/resource/anshi/mslist.html','w',encoding='utf-8')

# f = open('E:/QQ.csv','w',encoding='utf-8')

    # ---------------获得g_qzonetoken 和 gtk
    html = driver.page_source
    g_qzonetoken=re.search('window\.g_qzonetoken = \(function\(\)\{ try\{return (.*?);\} catch\(e\)',html)#从网页源码中提取g_qzonetoken
    g_qzonetoken = str(g_qzonetoken[0]).split('\"')[1]
    cookie = {}  # 初始化cookie字典
    for elem in driver.get_cookies():  # 取cookies
        cookie[elem['name']] = elem['value']

    gtk = getGTK(cookie)  # 通过getGTK函数计算gtk
    print(g_qzonetoken)
    print(gtk)
    last_source = ""
    QQnum = '2425936375'
    tag = 1
    # QQname = numList[QQnum]
    begin = 0
    first = 0

    myclient = pymongo.MongoClient("mongodb://47.93.226.47:27017/")
    mydb = myclient["sweet"]
    col = mydb['wall_emotions']
    # 打开数据库连接
    # db = pymysql.connect("47.93.226.47","hang","hang!@#","ttq" )

# 使用 cursor() 方法创建一个游标对象 cursor
    # cursor = db.cursor()
    while tag == 1:
        # -------------进入好友说说页面                                                                       #'+QQnum+'              '+str(begin)+'
        print("Begin:" + str(begin))
        driver.get(
            'https://user.qzone.qq.com/proxy/domain/taotao.qq.com/cgi-bin/emotion_cgi_msglist_v6?uin=' + QQnum + '&ftype=0&sort=0&pos=' + str(
                begin) + '&num=20&replynum=200&g_tk=' + str(
                gtk) + '&callback=_preloadCallback&code_version=1&format=jsonp&need_private_comment=1&qzonetoken=' + str(
                g_qzonetoken) + '&g_tk=' + str(gtk))
        print(
                'https://user.qzone.qq.com/proxy/domain/taotao.qq.com/cgi-bin/emotion_cgi_msglist_v6?uin=' + QQnum + '&ftype=0&sort=0&pos=' + str(
            begin) + '&num=20&replynum=200&g_tk=' + str(
            gtk) + '&callback=_preloadCallback&code_version=1&format=jsonp&need_private_comment=1&qzonetoken=' + str(
            g_qzonetoken) + '&g_tk=' + str(gtk))

        try:
            msg_list_json = driver.page_source
        except:
            begin = begin + 20
            continue

        msg_list_json = str(msg_list_json)
        # print(msg_list_json)
        # f1.write(msg_list_json)
        if last_source == msg_list_json:
            break
        else:
            last_source = msg_list_json

        # # '''
        # # 注意：爬虫不是黑客！！！！
        # #      还是要有权限的！！！
        # '''

        # 检测是否没有权限访问
        abtract_pattern = re.compile(',"message":"(.*?)","name":', re.S)
        message = re.findall(abtract_pattern, str(msg_list_json))
        if message != []:
            if str(message[0]) == '对不起,主人设置了保密,您没有权限查看':  # 对不起,主人设置了保密,您没有权限查看
                break

        # 解析JSON
        # webDriver没有现成的JSON解析器，所以采用获取源码的方式，然后使用正则表达式获取具体细节
        msg_list_json = msg_list_json.split("_preloadCallback(")[1]  # 拆分json，缩小范围，也能加快解析速度
        msg_list_json = msg_list_json.split(");</pre></body></html>")[0]
        # print(msg_list_json)
        # obj=json.loads(str)
        # print(obj.__class__)
        msg_dict = json.loads(msg_list_json)


        for emotion in msg_dict['msglist']:
            tid = emotion['tid']
            uin = emotion['uin']
            created = emotion['created_time']
            content = emotion['content']

            item = {
                'tid': tid,
                'uin': uin,
                'created': created,
                'content': content,
                'info': emotion
            }
            result = col.find_one({'tid': tid})
            # x = col.insert_one(item)
            # print(x.inserted_id)

            if result is None:
                x = col.insert_one(item)
                print("已添加")
                print(x.inserted_id)
            else:
                x = col.update({'tid': tid},item)
                print('已更新')
                print(tid)
            # print(result)
            # print(tid)
            # print('已存在')
            # print(item)
            # json_em = json.dumps(item)
            # print(json_em.decode('utf-8'))

            # sql = "INSERT INTO sw_wall(tid, content, created, uin, info) VALUES ('%s', '%s', '%s', '%s', '%s')" % \
            #         (tid,content,created,uin,str(emotion))
            # try:
            #     cursor.execute(sql)
            #     db.commit()
            # except (Exception) as args:
            #     print (args)
            #     print (emotion)
            #     db.rollback()
            #     break


            # pass
   #      print(msg_dict['msglist'][0])
   #      print(msg_dict['msglist'][0]['tid'])
   #      print(msg_dict['msglist'][0]['uin'])

   #      print(len(msg_dict['msglist']))

   #      sql = "INSERT INTO sw_wall(tid, \
   #              content, created, uin, image, info) \
   #              VALUES ('%s', '%s', '%s', '%s', '%s', '%s' )" % \
   #              ('Mac', 'Mohan', '10', 'M', '10', 'aa')
   #      try:
   # # 执行sql语句
   #          cursor.execute(sql)
   # # 执行sql语句
   #          db.commit()
   #      except:
   #          print ('error')
   # # 发生错误时回滚
   #          db.rollback()
        # print(msg_dict['msglist'].__class__)
        # # msg_list_json = msg_list_json.split("smoothpolicy")[0]
        # # msg_list_json = msg_list_json.split("commentlist")[1:]
        # # msg_list_json = msg_list_json.split("commentlist")[1:]

        # # 说说动态分4种：1、文字说说（或带有配图的文字说说）
        # #              2、只有图片的说说
        # #              3、转发，并配有文字
        # #              4、转发，不配文字

        # for text in msg_list_json:
        #     # 1、先检查说说，用户是否发送了文字，如果没有文字，正则表达式匹配无效
        #     # abtract_pattern = re.compile('\}\],"content":"(.*?)","createTime":"(.*?)","created_time":(.*?),"', re.S)
        #     abtract_pattern = re.compile('\}\],"content":"(.*?)","createTime":"(.*?)","created_time":(.*?)","editMask":(.*?),"', re.S)
        #     msg_time = re.findall(abtract_pattern, str(text))

        #     if msg_time != []:
        #         # 2、如果作者说说有文字，那么检查是否有转发内容
        #         msg = str(msg_time[0][0])
        #         sendTime = str(msg_time[0][1])
        #         print(msg_time)
        #         print(msg_time[0])
        #         print(msg_time[0][1])

        #         abtract_pattern = re.compile('\}\],"content":"(.*?)"},"rt_createTime":"(.*?)","', re.S)
        #         text = text.split("created_time")[1]
        #         msg_time2 = re.findall(abtract_pattern, str(text))

        #         # 合并发送内容 格式：评论+转发内容
        #         if msg_time2 != []:
        #             msg = msg + "  转发内容:" + str(msg_time2[0][0])

        #     else:
        #         # 3、说说内容为空，检查是否为 =>只有图片的说说 or 转发，不配文字
        #         # 获取正文发送时间 （发送时间分为：正文发送时间 or 转发时间）
        #         abtract_pattern = re.compile('"conlist":null,"content":"","createTime":"(.*?)",', re.S)
        #         msgNull_time = re.findall(abtract_pattern, str(text))

        #         if msgNull_time != []:
        #             # 如果有正文发送时间，那么就是这条说说仅含有图片  =>只有图片的说说
        #             msg = "图片"
        #             sendTime = str(msgNull_time[0])
        #         else:
        #             # 如果没有正文发送时间，那么就是说这条说为 =>转发，不配文字
        #             abtract_pattern = re.compile('\}\],"content":"(.*?)"},"rt_createTime":"(.*?)","', re.S)
        #             msg_time = re.findall(abtract_pattern, str(text))
        #             msg = "  转发内容:" + str(msg_time[0][0])
        #             sendTime = str(msg_time[0][1])

        #     f.write('{},{},{}\n'.format(str(QQnum), sendTime, msg))
        #     print(str(QQnum) + " : " + sendTime + " : " + msg)
        if begin == 1000:
            tag = 0
        begin = begin + 20
        time.sleep(2)


def getGTK(cookie):
    hashes = 5381
    for letter in cookie['p_skey']:
        hashes += (hashes << 5) + ord(letter)
    return hashes & 0x7fffffff
# print('enter')
# schedule.enter(30*60,0,get_emotion,())
# schedule.run()
get_emotion()
