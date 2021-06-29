## 版权声明
本作品采用<a rel="license" href="http://creativecommons.org/licenses/by/4.0/">知识共享署名 4.0 国际许可协议</a>进行许可。
本文作者：低调小熊猫
文章链接：https://aodeng.cc/archives/springbootliu
转载声明：自由转载-非商用-非衍生-保持署名，非商业转载请注明作者及出处，商业转载请联系作者本人qq:2696284032

## 单纯的广告
**个人博客：https://aodeng.cc**
**微信公众号：低调小熊猫**
**qq交流群：756796932**

## Docker 部署 Spring Boot
**什么是Docker**
[https://yeasy.gitbooks.io/docker_practice/introduction/what.html](https://yeasy.gitbooks.io/docker_practice/introduction/what.html "https://yeasy.gitbooks.io/docker_practice/introduction/what.html")

## 配置
**简单的springboot配置省略**
**加上docker.image.prefix和Docker maven plugin**
```
<properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <java.version>1.8</java.version>
        <docker.image.prefix>springboot</docker.image.prefix>
    </properties>

	<!-- Docker maven plugin -->
	<plugin>
		<groupId>com.spotify</groupId>
		<artifactId>docker-maven-plugin</artifactId>
		<version>1.0.0</version>
		<configuration>
			<imageName>${docker.image.prefix}/${project.artifactId}</imageName>
			<dockerDirectory>src/main/docker</dockerDirectory>
			<resources>
				<resource>
					<targetPath>/</targetPath>
					<directory>${project.build.directory}</directory>
					<include>${project.build.finalName}.jar</include>
				</resource>
			</resources>
		</configuration>
	</plugin>
	<!-- Docker maven plugin -->
```
## 创建文件
创建src/main/docker目录，在目录下创建Dockerfile文件
文件内容如下：
```
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD spring-boot-docker-1.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
```
FROM ，表示使用 Jdk8 环境 为基础镜像，如果镜像不是本地的会从 DockerHub 进行下载
VOLUME ，VOLUME 指向了一个/tmp的目录，由于 Spring Boot 使用内置的Tomcat容器，Tomcat 默认使用/tmp作为工作目录。这个命令的效果是：在宿主机的/var/lib/docker目录下创建一个临时文件并把它链接到容器中的/tmp目录
ADD ，拷贝文件并且重命名
ENTRYPOINT ，为了缩短 Tomcat 的启动时间，添加java.security.egd的系统属性指向/dev/urandom作为 ENTRYPOINT
## 打包部署
1.服务器安装 Docker 环境(百度一片)记得加入开机启动
2.服务器安装项目运行环境jdk，maven那些(百度一片)记得加入开机启动
3.将本地spring-boot-docker项目放到服务器上
```
#打包
mvn package
#启动
java -jar target/spring-boot-docker-1.0.jar
```
4.启动成功开始下一步，使用 DockerFile 构建镜像
```
mvn package docker:build
```
需要等一会，使用docker images命令查看构建好的镜像
5.运行镜像
```
docker run -p 8080:8080 -t springboot/spring-boot-docker
```
6.使用docker ps查看正在运行的镜像，如果有，使用docker部署springboot就成功了