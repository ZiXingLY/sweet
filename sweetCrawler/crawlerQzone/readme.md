### crawlerQzone 0.0.1

argv init uin mongo

# usege 
使用 `python3 start.py` 启动
可附加参数，使用 `python3 start.py -t` 爬取贴吧君数据

- 启用 restful 服务 `python3 lavish_time_with_you_is_true_funny.py`

- 启用 restful 服务调用 `http://localhost:8687/start/qqnum`
```
wget http://localhost:8687/start/449338017

curl -d “userName=tom&passwd=123456” -X POST http://localhost:8687/

curl -X POST http://localhost:8687/todos
```
