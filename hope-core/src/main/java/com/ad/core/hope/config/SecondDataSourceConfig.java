package com.ad.core.hope.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-08-02 20:16
 **/

@Configuration
@MapperScan(basePackages = "com.ad.core.hope.mapper.second",sqlSessionTemplateRef = "secondSqlSessionTemplate")
public class SecondDataSourceConfig {

    @Autowired
    private SecondDataProperties secondprop;

    //创建数据源
    @Bean(name="secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    @Primary
    public DataSource getSecondDataSource(){
        DataSource build = DataSourceBuilder.create()
                .driverClassName(secondprop.driverClassName)
                .url(secondprop.url)
                .username(secondprop.username)
                .password(secondprop.password).build();
        return build;
    }

    //创建SqlSessionFactory
    @Bean(name = "secondSqlSessionFactory")
    @Primary
    public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    //创建事务管理器
    @Bean(name = "secondTransactionManager")
    @Primary
    public DataSourceTransactionManager secondTransactionManager(@Qualifier("secondDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    //创建SqlSessionTemplate
    @Bean(name = "secondSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate secondSqlSessionTemplate(@Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws  Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    private Class getType(String type){
        try {
            return Class.forName(type);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
