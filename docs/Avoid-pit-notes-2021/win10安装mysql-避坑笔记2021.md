## win10安装mysql-避坑笔记2021

一、官网下载window版本mysql5.7解压版

二、解压到安装的目录，配置环境变量

三、创建my.ini配置文件 创建data文件夹 配置路径地址
```
[mysql]
# 设置mysql客户端默认字符集
default-character-set=utf8
[mysqld]
#设置3306端口
port = 3306
#使得MySQL将不再通过DNS解析地址
#skip-host-cache
#skip-name-resolve
# 设置mysql的安装目录
basedir=D:\a_java_device_jar\mysql-5.7.27-winx64
# 设置mysql数据库的数据的存放目录
datadir=D:\a_java_device_jar\mysql-5.7.27-winx64\data
# 允许最大连接数
max_connections=200
# 服务端使用的字符集默认为8比特编码的latin1字符集
character-set-server=utf8
# 创建新表时将使用的默认存储引擎
default-storage-engine=INNODB
```
四、安装服务 cmd进入mysql bin目录执行命令：

```
1、安装服务
mysqld --install

2、如果提示有服务存在，卸载
sc delete mysql

3、初始化配置文件
mysqld --initialize-insecure --user=mysql

4、安装成功启动mysql服务（关闭stop）
net start mysql

5、启动成功进入服务，第一次登录没密码，提示输入密码回车即可
mysql -u root -p

6、进入服务第一步修改root账号密码 并刷新
mysql> use mysql;
mysql> update user set authentication_string=PASSWORD("root") where user="root";
mysql> flush privileges;

7、进入服务第二步开启远程ip访问服务权限 并刷新
mysql> use mysql;
mysql> update user set host='%' where user='root';
mysql> flush privileges;

8、退出服务
mysql> quit;
```

* 扩展
出现group by 语句不兼容问题解决方案 ：修改sqlmodel 两个都执行
```
set @@global.sql_mode ='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
set sql_mode ='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
```
