## mvn常用命令

> 熊猫笔记 https://github.com/java-aodeng/hope
    
    打包跳过测试
    mvn package -Dmaven.test.skip=true
    
    maven导入本地jar
    1.创建resources/lib目录，导入jar
    2.mavne添加dependencies依赖
	<dependency>
    	<groupId>ocean.client.java.biz</groupId>
    	<artifactId>ocean.client.java.biz</artifactId>
    	<version>1.0.0</version>
    	<scope>system</scope>
    	<systemPath>${project.basedir}/src/main/resources/lib/ocean.client.java.biz.jar</systemPath>
    </dependency>
    3.打包添加config
    <configuration>
		<includeSystemScope>true</includeSystemScope>
	</configuration>
    
