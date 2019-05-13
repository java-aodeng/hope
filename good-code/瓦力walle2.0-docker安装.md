# 瓦力walle2.0-docker安装
walle 让用户代码发布终于可以不只能选择 jenkins！支持各种web代码发布，php、java、python、go等代码的发布、回滚可以通过web来一键完成。walle 一个可自由配置项目，更人性化，高颜值，支持git、多用户、多语言、多项目、多环境同时部署的开源上线部署系统。官网：http://walle-web.io/

一、安装前

    查看环境：
    $ head -n 1 /etc/issue # 查看操作系统版本 
    $ mysql -V #查看mysql版本 MySQL 5.6.5以上
    $ curl ifconfig.me # 查看外网ip
    $ hostname # 查看计算机名
    $ telnet  主机 22 #看是否有反应
    
    将所有目标主机(部署项目所在机器) 加入 宿主机(walle安装所在机器)ssh免密登录配置：
    命令行（适合机器数量少的）：
    $ ssh-keygen -t rsa #生成SSH密钥和公钥
    $ ssh-copy-id -i ~/.ssh/id_rsa.pub 目标主机名@目标主机ip #在宿主机执行
    $ 输入 目标主机 密码
    $ ssh '目标主机名@目标主机ip' #链接目标主机看是否成功
    $ exit; #退出当前主机链接
    
    注意：
        免密码登录需要远程机器权限满足以下三个条件：
        ~ 755
        ~/.ssh 700
        ~/.ssh/authorized_keys 644 或 600
        
二、Docker安装
    
    查看环境：
    $ docker -v #查看docker版本

    install docker-compose:
    $ pip install docker-compose -i https://mirrors.aliyun.com/pypi/simple/
    
    如果pip不存在，可以尝试(centos):
    $ sudo yum install python-pip 
    $ sudo pip install --upgrade pip
    
    ubuntu 安装pip
    # 1. 更新系统包
    $ sudo apt-get update
    $ sudo apt-get upgrade
    # 2. 安装Pip
    $ sudo apt-get install python-pip
    # 3. 检查 pip 是否安装成功
    $ pip -V
    
    创建docker-compose
    # 1.创建文件夹 walle 目录 usr/local/src 下执行
    $ sudo mkdir walle
    # 2.创建docker-compose.yml walle.env 两个文件到walle目录下
    $ sudo vim docker-compose.yaml
    写入：
        # docker version:  18.06.0+
        # docker-compose version: 1.23.2+
        # OpenSSL version: OpenSSL 1.1.0h
        version: "3.7"
        services:
          web:
            image: alenx/walle-web:2.1
            container_name: walle-nginx
            hostname: nginx-web
            ports:
              # 如果宿主机80端口被占用，可自行修改为其他port(>=1024)
              # 0.0.0.0:要绑定的宿主机端口:docker容器内端口80
              - "80:80"
            depends_on:
              - python
            networks:
              - walle-net
            restart: always
        
          python:
            image: alenx/walle-python:2.1
            container_name: walle-python
            hostname: walle-python
            env_file:
              # walle.env需和docker-compose在同级目录
              - ./walle.env
            command: bash -c "cd /opt/walle_home/ && /bin/bash admin.sh migration &&  python waller.py"
            expose:
              - "5000"
            volumes:
              - /opt/walle_home/plugins/:/opt/walle_home/plugins/
              - /opt/walle_home/codebase/:/opt/walle_home/codebase/
              - /opt/walle_home/logs/:/opt/walle_home/logs/
              - /root/.ssh:/root/.ssh/
            depends_on:
              - db
            networks:
              - walle-net
            restart: always
        
          db:
            image: mysql
            container_name: walle-mysql
            hostname: walle-mysql
            env_file:
              - ./walle.env
            command: [ '--default-authentication-plugin=mysql_native_password', '--character-set-server=utf8mb4', '--collation-server=utf8mb4_unicode_ci']
            ports:
              - "3306:3306"
            expose:
              - "3306"
            volumes:
              - /data/walle/mysql:/var/lib/mysql
            networks:
              - walle-net
            restart: always
        
        networks:
          walle-net:
            driver: bridge
    $ sudo vim walle.env
    写入：
        # Set MySQL/Rails environment
        MYSQL_USER=root
        MYSQL_PASSWORD=walle
        MYSQL_DATABASE=walle
        MYSQL_ROOT_PASSWORD=walle
        MYSQL_HOST=db
        MYSQL_PORT=3306
        
        注意：记得修改数据库端口和walle端口，也仅仅需要修改端口即可，别把时间花在部署上
    
    启动
    $ docker-compose up -d && docker-compose logs -f
    访问：ip：端口 
    初始登录账号如下，开启你的walle 2.0之旅吧：）
    超管：super@walle-web.io \ Walle123
    所有者：owner@walle-web.io \ Walle123
    负责人：master@walle-web.io \ Walle123
    开发者：developer@walle-web.io \ Walle123
    访客：reporter@walle-web.io \ Walle123
    
    遇到的问题及解决方案：
    
        报错: Couldn't connect to Docker daemon at http+docker://localunixsocket - is it running?
        解决：将当前用户加入docker组：
        $ sudo gpasswd -a ${USER} docker
        参考博客：
        https://blog.csdn.net/xiojing825/article/details/79494408
        https://www.cnblogs.com/tianhei/p/7802064.html
        
        docker-compose源的最新版地址：https://github.com/docker/compose/releases
        docker常用命令:https://blog.csdn.net/EasternUnbeaten/article/details/80463837
        
        docker-compose卸载：
        $ sudo rm /usr/local/bin/docker-compose #二进制
        $ sudo pip uninstall docker-compose #pip
        $ apt --purge remove docker-compose #ap

三、数据映射

    宿主机的路径:容器的路径
    
    这是walle的数据映射
    volumes:
      - /opt/walle_home/plugins/:/opt/walle_home/plugins/
      - /opt/walle_home/codebase/:/opt/walle_home/codebase/
      - /opt/walle_home/logs/:/opt/walle_home/logs/
      - /root/.ssh:/root/.ssh/
      
    注：docker如果不做数据映射，容器没了，数据也就没了
    
    chmod 777 ./logs
