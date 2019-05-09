package com.hope.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @program:hope
 * @ClassName:GengeratorTest
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-04-24 14:49
 * @Description: mybatis-plus 代码生成器
 * @Version 1.0
 **/
public class GengeratorTestA {

    public static void main(String[] args) {
        String packageName="com.hope.generator";
        //user -> UserService, 设置成true: user -> IUserService
        boolean serviceNameStartWithI=false;

        //mybatis-plus generator代码生成 1
        GengeratorTestA.generatorByTables(serviceNameStartWithI,packageName,"sys_resource","sys_role");
    }

    private static void generatorByTables(boolean serviceNameStartWithI,String packageName,String... tableNames){
        GlobalConfig globalConfig=new GlobalConfig();

        String dbUrl="jdbc:mysql://localhost:3306/test";

        DataSourceConfig dataSourceConfig=new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("123456")
                .setDriverName("com.mysql.cj.jdbc.Driver");

        StrategyConfig strategyConfig=new StrategyConfig();
        strategyConfig.setCapitalMode(true)
                .setEntityLombokModel(false)
                //.setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);

        globalConfig.setActiveRecord(true)
                .setAuthor("低调小熊猫")
                .setOutputDir("d:\\codeGen")
                .setFileOverride(true);

        if(!serviceNameStartWithI){
            globalConfig.setServiceName("%sService");
        }

        new AutoGenerator().setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig().setParent(packageName)
                        .setController("controller")
                        .setEntity("entity")
                ).execute();
    }

    private static void generatorByTables(String packageName,String... tableNames){
        generatorByTables(true,packageName,tableNames);
    }
}
