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
@MapperScan(basePackages = "com.ad.core.hope.mapper.first",sqlSessionTemplateRef = "firstSqlSessionTemplate")
public class FirstDataSourceConfig {

    @Autowired
    private FirstDataProperties firstprop;

    //创建数据源
    @Bean(name="firstDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.first")

    public DataSource getFirstDataSource(){
        DataSource build = DataSourceBuilder.create()
                .driverClassName(firstprop.driverClassName)
                .url(firstprop.url)
                .username(firstprop.username)
                .password(firstprop.password).build();
     return build;
    }

    //创建SqlSessionFactory
    @Bean(name = "firstSqlSessionFactory")

    public SqlSessionFactory firstSqlSessionFactory(@Qualifier("firstDataSource") DataSource dataSource) throws Exception{
     SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
     bean.setDataSource(dataSource);
     return bean.getObject();
    }

    //创建事务管理器
    @Bean(name = "firstTransactionManager")

    public DataSourceTransactionManager firstTransactionManager(@Qualifier("firstDataSource") DataSource dataSource){
     return new DataSourceTransactionManager(dataSource);
    }

    //创建SqlSessionTemplate
    @Bean(name = "firstSqlSessionTemplate")

    public SqlSessionTemplate firstSqlSessionTemplate(@Qualifier("firstSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws  Exception{
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
