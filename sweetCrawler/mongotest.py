#!/usr/bin/python3
 
import pymongo

myclient = pymongo.MongoClient("mongodb://47.93.226.47:27017/")
mydb = myclient["sweet"]
col = mydb['wall_emotions']
mydict = { "name": "Google", "alexa": "1", "url": "https://www.google.com" }
x = col.insert_one(mydict)
print(x.inserted_id)
# dblist = myclient.list_database_names()
# print(dblist)

# "{ "certified" : 0 , "cmtnum" : 2 , "commentlist" : [ { "IsPasswordLuckyMoneyCmtRight" : "" , "abledel" : 0 , "content" : "卡贴真好看 优秀[em]e400623[/em]" , "createTime" : "10:58" , "createTime2" : "2018-09-28 10:58:24" , "create_time" : 1538103504 , "name" : "水晶001🤘[em]e327996[/em]" , "private" : 0 , "reply_num" : 0 , "source_name" : "" , "source_url" : "" , "t2_source" : 1 , "t2_subtype" : 2 , "t2_termtype" : 2 , "tid" : 1 , "uin" : 1843650493} , { "IsPasswordLuckyMoneyCmtRight" : "" , "abledel" : 0 , "content" : "千玺大佬" , "createTime" : "10:58" , "createTime2" : "2018-09-28 10:58:26" , "create_time" : 1538103506 , "name" : "诡" , "private" : 0 , "reply_num" : 0 , "source_name" : "" , "source_url" : "" , "t2_source" : 1 , "t2_subtype" : 2 , "t2_termtype" : 2 , "tid" : 2 , "uin" : 1281076619}] , "conlist" :  null  , "content" : "" , "createTime" : "10:54" , "created_time" : 1538103283 , "editMask" : 4294967294 , "fwdnum" : 0 , "has_more_con" : 0 , "isEditable" : 1 , "issigin" : 0 , "lbs" : { "id" : "" , "idname" : "" , "name" : "" , "pos_x" : "" , "pos_y" : ""} , "name" : "解忧于你[em]e327811[/em]校园表白墙" ,
 # "pic_template" : "" , "right" : 1 , "rt_sum" : 0 , "secret" : 0 , "source_appid" : "" , "source_name" : "墙哥不吃饭省来的iPhone X (4G)" , "source_url" : "" , "t1_source" : 1 , "t1_subtype" : 2 , "t1_termtype" : 3 , "tid" : "f7d99890f397ad5b73420300" , "ugc_right" : 1 , "uin" : 2425936375 , "video" : [ { "absolute_position" : 0 , "cover_height" : 1280 , "cover_width" : 592 , "upload" : 0 , 
 # "url1" : "http://b290.photo.store.qq.com/psb?/V11Xtz6l0MndjM/OWe77eub35QKaSJbYxTAVzA6Q37ZuYgw7qU.wb3uGm4!/c/dCIBAAAAAAAA&amp;bo=dgOABwAAAAARF9I!" , "url2" : "" , "url3" : "http://vwecam.gtimg.com/1006_a6f0f160d1ab4f20be21502f3152ccbb.f0.mp4?ptype=http&amp;vkey=D06DA137BA171311E961D1AF236DB8F70F25979889C5A5E62F90C938749A9ACBAFC864763B97F3BC13A84D51CDCD313347AFC308109D113F&amp;sdtfrom=v1000&amp;owner=0" , "video_id" : "1006_a6f0f160d1ab4f20be21502f3152ccbb" , "video_right" : 0 , "video_status" : 1}] , "videototal" : 1 , "wbid" : 0}"
