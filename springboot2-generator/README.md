## 简介
**Mybatis-Generator是Mybatis提供的一个便捷型插件，自动可以为项目生产对应的实体类，Mapper，dao层。**
官网文档：[链接](http://www.mybatis.org/generator/index.html "链接")
本作品采用<a rel="license" href="http://creativecommons.org/licenses/by/4.0/">知识共享署名 4.0 国际许可协议</a>进行许可。
版权声明：本文由 低调小熊猫 发表于 低调小熊猫的博客
转载声明：自由转载-非商用-非衍生-保持署名，非商业转载请注明作者及出处，商业转载请联系作者本人qq:2696284032
文章链接：https://aodeng.cc/archives/springboot-er

## 单纯的广告
**个人博客：https://aodeng.cc**
**微信公众号：低调小熊猫**
**qq交流群：756796932**

## 使用
**添加依赖**
```
<!-- SpringBoot mybatis generator插件-->
<plugin>
	<groupId>org.mybatis.generator</groupId>
	<artifactId>mybatis-generator-maven-plugin</artifactId>
	<version>1.3.2</version>
	<configuration>
		<configurationFile>${basedir}/src/main/resources/generator/generatorConfig.xml</configurationFile>
		<verbose>true</verbose>
		<overwrite>true</overwrite>
	</configuration>
</plugin>
```
**配置Generator**
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <properties resource="application.yml"/>

    <!-- 指定使用jar路径-->
    <classPathEntry
            location="C:\z_java_resources\apache-maven-repo\mysql\mysql-connector-java\5.1.34\mysql-connector-java-5.1.34.jar" />
    <context id="Mysql" targetRuntime="MyBatis3Simple" defaultModelType="flat">
        <property name="beginningDelimiter" value="`"/>
        <property name="endingDelimiter" value="`"/>
		
        <!-- 连接参数-->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/hope"
                        userId="root"
                        password="123456">
        </jdbcConnection>

        <!--生成Model类存放位置-->
        <javaModelGenerator targetPackage="com.ad.core.hope.model.admin" targetProject="src/main/java"></javaModelGenerator>
        <!--生成映射文件存放位置-->
        <sqlMapGenerator targetPackage="mapper.admin" targetProject="src/main/resources"></sqlMapGenerator>
        <!--生成Dao类存放位置-->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.ad.core.hope.service.admin" targetProject="src/main/java"></javaClientGenerator>
        <table tableName="%">
            <!-- mysql配置 -->
            <generatedKey column="id" sqlStatement="Mysql" identity="true"/>
        </table>
    </context>
</generatorConfiguration>

```
**idea开发，还需要配置maven的启动**
```
mybatis-generator:generate -e
```
