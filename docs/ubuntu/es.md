## Ubuntu下Elasticsearch安装配置
安装JDK8
如果已经安装了jdk，直接下一步。

```
sudo apt-get install openjdk-8-jdk
```
下载Elasticsearch
进入https://www.elastic.co/cn/downloads/elasticsearch查看最新版本，并获取到链接。

```
sudo wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-6.1.1.tar.gz
sudo tar -zvxf elasticsearch-6.1.1.tar.gz
sudo mv elasticsearch-6.1.1 elasticsearch
```

我存放的路径是/usr/web下

新建目录
用户存储日志和数据信息
```
sudo mkdir /usr/web/es/logs
sudo mkdir /usr/web/es/data
sudo chmod 777 -R /usr/web/es
```
配置Elastic
sudo vim /usr/web/elasticsearch/conf/elasticsearch.yml
```
#集群名字，es启动后会将具有相同集群名字的节点放到一个集群下。
cluster.name: es_cluster
#节点名字
node.name: node-1
#存储文件位置
path.data: /usr/web/es/data
#日志文件位置
path.logs: /usr/web/es/logs
#默认设置成false,即ES节点允许内存交换
bootstrap.memory_lock: false
#这个建议加上，如果不加可能会因为内核不匹配启动失败
bootstrap.system_call_filter: false
#设置绑定的ip地址
network.host: 0.0.0.0
#端口号
http.port: 9200
```
sudo vim /usr/web/elasticsearch/conf/jvm.options

```
#默认是1G，如果内存够用可以不改
-Xms512M
-Xmx512M
```

配置环境变量

```
sudo vim /etc/profile    
export ES_HOME=/usr/web/elasticsearch
source /etc/profile  
```

替换文件
以上步骤完成后启动仍然会报错，需要替换下lib文件夹下的jna-4.4.0-1.jar文件

```
sudo cd /usr/web/elasticsearch/lib
sudo mv jna-4.4.0-1.jar jna-4.4.0-1.jar.bak
sudo wget http://repo1.maven.org/maven2/net/java/dev/jna/jna/4.4.0/jna-4.4.0.jar
sudo mv jna-4.4.0.jar jna-4.4.0-1.jar
```

修改配置
sudo vim /etc/sysctl.conf

```
kernel.sysrq = 1
net.ipv6.conf.all.disable_ipv6 = 1
net.ipv6.conf.default.disable_ipv6 = 1
vm.max_map_count=655360
```
修改完成后执行

```
sudo sysctl -p
```
添加用户启动服务
```
sudo groupadd elsearch
sudo useradd elsearch -g elsearch
sudo chown -R elsearch:elsearch /usr/web/elasticsearch
sudo passwd elsearch
sudo su elsearch
/usr/web/elasticsearch/bin/elasticsearch
```
访问
启动完成后，访问 http://ip地址:9200/
显示数据格式为
```
{
  "name" : "node-1",
  "cluster_name" : "es_cluster",
  "cluster_uuid" : "587xwKpeTam_Kja5JlMDRQ",
  "version" : {
    "number" : "6.1.1",
    "build_hash" : "bd92e7f",
    "build_date" : "2017-12-17T20:23:25.338Z",
    "build_snapshot" : false,
    "lucene_version" : "7.1.0",
    "minimum_wire_compatibility_version" : "5.6.0",
    "minimum_index_compatibility_version" : "5.0.0"
  },
  "tagline" : "You Know, for Search"
}
```
以上为单台服务器配置，多台与单台类似，可移步http://blog.csdn.net/sinat_28224453/article/details/51134978