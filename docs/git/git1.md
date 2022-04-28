# Git知识

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
    
    查看远程仓库地址命令
    git remote -v

    修改远程仓库的 url
    git remote set-url origin <URL>
    
    清空缓存
    git rm -r --cached . 
    
    设置自己的名字
    git config --global user.name "java-aodeng"
    
    设置自己的邮箱
    git config --global user.email "java@aodeng.cc"
    然后一定要再次确认，再次输入
    git config --global user.email
    
    git branch -l :查看本地分支

    git branch -r :查看远程分支

    git branch -a :查看全部分支（远程的和本地的）
    
    删除分支
    git push origin :【需要删除的分支名字】
    例：删除feature分支：(git push origin :feature)
    
三、git代理配置

    设置代理
    git config --global https.proxy [http://127.0.0.1:1080](http://127.0.0.1:1080/)
    
    git config --global https.proxy [https://127.0.0.1:1080](https://127.0.0.1:1080/)
    
    取消代理
    git config --global --unset http.proxy
    
    git config --global --unset https.proxy

四、错误及解决方法
    
    push出现这个错误：
    Your branch is based on 'origin/master', but the upstream is gone.
    原因：本地仓库与远程仓库当前分支连接失效
    解决：
    1.本地仓库创建一个新分支
    git branch release-aodeng-0514
    2.推送这个分支到远程仓库
    git push origin release-aodeng-0514

五、仓库.git文件太大，push太慢，一个老套的解决方案：（这样将克隆只有一个提交历史记录的存储库。因此，您的.git文件夹将更轻）
    
    git clone --depth 1 https://github.com/java-aodeng/hope.git
    
六、仓库地址切换，修改了仓库地址后，第一次强制推送
    
    git push origin HEAD --force
