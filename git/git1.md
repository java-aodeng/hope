# git常用命令

一、git常用命令（实用版）

    git init 初始化仓库
    git status 查看git状态
    git add .  
    git commit -m '注释' 
    git remote add origin 仓库地址
    git pull origin master 拉取 （注意分支名）
    git push -u origin master 推送（注意分支名）
    git checkout 分支名 切换分支
    git remote update origin --prune 切换远程分支
    git diff <版本1> <版本2> 比较两个版本的区别
    git log 可以查看 git 的歷史以及版本號（按 q 退出） git log --oneline 看第一次
    
二、git常用命令（高级版）

    抛弃本地所有的修改，回到远程仓库的状态:
    git fetch --all && git reset --hard origin/master
    
    修改远程仓库的 url
    git remote set-url origin <URL>
    
    清空缓存
    git rm -r --cached . 
    
    设置自己的名字
    git config --global user.name "设置自己的名字"
    
    设置自己的邮箱
    git config --global user.email "设置自己的邮箱"
    然后一定要再次确认，再次输入
    git config --global user.email
    
    git branch -l :查看本地分支

    git branch -r :查看远程分支

    git branch -a :查看全部分支（远程的和本地的）
    
三、git代理配置

    设置代理
    git config --global https.proxy [http://127.0.0.1:1080](http://127.0.0.1:1080/)
    
    git config --global https.proxy [https://127.0.0.1:1080](https://127.0.0.1:1080/)
    
    取消代理
    git config --global --unset http.proxy
    
    git config --global --unset https.proxy    
