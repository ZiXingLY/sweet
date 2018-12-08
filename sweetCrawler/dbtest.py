#!/usr/bin/python3
 
import pymysql
 
# 打开数据库连接
db = pymysql.connect("47.93.226.47","hang","hang!@#","ttq" )

# 使用 cursor() 方法创建一个游标对象 cursor
cursor = db.cursor()

sql = "INSERT INTO sw_wall(tid, \
       content, created, uin, image, info) \
       VALUES ('%s', '%s', '%s', '%s', '%s', '%s' )" % \
       ('Mac', 'Mohan', '10', 'M', '10', 'aa')
try:
   # 执行sql语句
   cursor.execute(sql)
   # 执行sql语句
   db.commit()
   print ('success')
except:
   print ('error')
   # 发生错误时回滚
   db.rollback()
 
# 使用 execute()  方法执行 SQL 查询 
# cursor.execute("SELECT VERSION()")
 
# 使用 fetchone() 方法获取单条数据.
# data = cursor.fetchone()
 
# print ("Database version : %s " % data)
 
# 关闭数据库连接
db.close()