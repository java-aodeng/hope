## CI/CD流程以及原理说明

> 熊猫笔记 https://github.com/java-aodeng/hope

### CI流程图
![](https://github.com/java-aodeng/hope/blob/master/ci-cd/%E5%9B%BE%E7%89%871.png)

### 开发发布流程/分支管理
1.开发基于release(从master建出)分支,命名为release-${主导开发人员名字}-日期 如release-aodeng-0814(release-hotfix-aodeng-0814),一个release分支代表一个上线计划

2.开发完成自测完成后推送代码后Gitlab执行CI脚本分别部署到开发/测试服务器,并通知测试人员进行测试(无法通过测试的情况反复提测至测试通过).

3.测试通过后由相关负责人修改版本号并merge代码至master分支,合并完成后会触发Gitlab-CI的生产环境部署任务.(此时需注意发布顺序,以项目依赖关系为准)

4.验证生产环境

### 开发流程例图
![](https://github.com/java-aodeng/hope/blob/master/ci-cd/%E5%9B%BE%E7%89%872.png)

### 和标准Git-Flow的区别
![](https://github.com/java-aodeng/hope/blob/master/ci-cd/%E5%9B%BE%E7%89%873.png)

### CI脚本详解
![](https://github.com/java-aodeng/hope/blob/master/ci-cd/%E5%9B%BE%E7%89%874.jpg)

### 技术栈/关键词

- Gitlab-CI (自动化)
- Docker (容器引擎)
- Maven (Java项目生命周期管理工具)
- Google Jib Maven Plugin (OCI镜像构建插件)
- Nexus (Maven仓库/Docker镜像仓库)
- 目标: DevOps / CI / CD
