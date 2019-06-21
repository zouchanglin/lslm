# 系统架构
SpringBoot + MySQL + Nginx代理服务


# MySQL数据库说明
* activity_info 存的是活动相关的数据

* user_info 存的是用户信息

* company_info 存的是入驻公司信息

* part_info 存的是具体的兼职信息
* part_category 存的是兼职的分类信息

# 用户分类说明
* user：普通用户（学生）
* company：企业用户（活动发布方）
* admin：管理员



# Nginx配置

```
worker_processes  1;

events {
    worker_connections  1024;
}

http {
    include       mime.types;
    default_type  application/octet-stream;
    sendfile        on;
    keepalive_timeout  65;

    server {
        listen       80;
        server_name  sell.com;

        location / {
            root   /opt/data/wwwroot/sell;
            index  index.html index.htm;
        }
    		location /sell/ {
            proxy_pass http://192.168.1.101:8080/sell/;(Tim的服务器地址)
    		}
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
    }
}
```

