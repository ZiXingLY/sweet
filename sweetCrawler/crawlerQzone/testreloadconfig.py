import configparser
import time

def test():
    flag = 1
    while flag == 1:
        time.sleep(2)
        config()

def config():
    conf = configparser.ConfigParser()
    conf.read("config.ini")
    get_number_per_crawler = conf.get("control", 'get_number_per_crawler')
    print(get_number_per_crawler)

test()