## Ubuntu下搭建Elasticsearch和kibana-熊猫哥版本

## 1-搭建Elasticsearch

本少都是看的官方文档，跟着我，稳得一匹 https://www.elastic.co/

这里呢，建议使用软件包统一管理，dpkg是个不错的选择，运维靠大家，方便你我他

安装DEB版本
```
cd develop/

wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.2.0-amd64.deb

dpkg -i elasticsearch-7.2.0-amd64.deb

修改config/elasticsearch.yml下的server.host为0.0.0.0 端口默认 jvm默认1G，怂的可自行调成512M

systemctl status elasticsearch.service
systemctl start elasticsearch.service
systemctl restart elasticsearch.service
``` 

## 遇到的坑-解决方案

Bootstrap Checks 编辑
总的来说，我们在遇到意外问题的用户方面有很多经验，因为他们没有配置 重要的设置。在以前的Elasticsearch版本中，其中一些设置的错误配置被记录为警告。可以理解，用户有时会错过这些日志消息。为了确保这些设置得到应有的关注，Elasticsearch在启动时进行了引导检查。

这些引导程序检查检查各种Elasticsearch和系统设置，并将它们与对Elasticsearch操作安全的值进行比较。如果Elasticsearch处于开发模式，则任何引导检查失败都会在Elasticsearch日志中显示为警告。如果Elasticsearch处于生产模式，则任何引导检查失败都会导致Elasticsearch拒绝启动。

有一些引导程序检查始终强制执行，以防止Elasticsearch使用不兼容的设置运行。这些检查单独记录。

开发与生产模式编辑
默认情况下，Elasticsearch绑定到HTTP 和传输（内部）通信的环回地址。这适用于下载和使用Elasticsearch以及日常开发，但它对生产系统毫无用处。要加入群集，必须通过传输通信访问Elasticsearch节点。要通过非环回地址加入集群，节点必须将传输绑定到非环回地址，而不是使用单节点发现。因此，如果Elasticsearch节点无法通过非环回地址与另一台机器形成集群，则我们认为它处于开发模式，如果它可以通过非环回地址加入集群，则处于生产模式。

注意，HTTP和传输可以通过http.host和独立配置 transport.host; 这可以用于将单个节点配置为可通过HTTP访问以进行测试，而不会触发生产模式。

单节点发现编辑
我们认识到一些用户需要将传输绑定到外部接口以测试其对传输客户端的使用。对于这种情况，我们提供发现类型single-node（通过设置discovery.type为 配置single-node）; 在这种情况下，节点将选择自己的主节点，并且不会与任何其他节点加入集群。

## 2-Ubuntu安装kibana

```
cd develop/
    
wget https://artifacts.elastic.co/downloads/kibana/kibana-7.2.0-amd64.deb

dpkg -i kibana-7.2.0-amd64.deb

修改config/kibaba.yml下的server.host为0.0.0.0 端口默认

systemctl start kibana.service
```
