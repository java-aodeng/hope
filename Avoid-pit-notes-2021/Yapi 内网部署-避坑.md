# Yapi 内网部署-避坑

## 开源地址

    https://github.com/YMFE/yapi

## 内网部署

方式一. 可视化部署:
    https://hellosean1025.github.io/yapi/devops/index.html

## 问题-解决

如果我们把cmd窗口关闭了，这时候我们就无法访问Yapi了，这时候我们就可以使用 pm2 管理 node 服务器启动，停止

1：安装pm2 安装需要等待一会

```
npm i -g pm2
```

2：使用pm2管理yapi服务

>进入Yapi的部署目录执行

```
pm2 start "vendors/server/app.js" --name yapi
```

pm2操作Yapi基本命令：

```
pm2 info yapi //查看服务信息
pm2 start yapi //停止服务
pm2 stop yapi //停止服务
pm2 restart yapi //重启服务
```
