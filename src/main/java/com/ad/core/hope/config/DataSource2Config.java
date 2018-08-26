package com.ad.core.hope.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
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
/*
@Configuration
@MapperScan(basePackages = "com.ad.core.service.service2,",sqlSessionTemplateRef = "source2SqlSessionTemplate")
public class DataSource2Config {

    @Bean(name="source2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.source2")
    @Primary
    public DataSource sourceDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "source2SqlSessionFactory")
    @Primary
    public SqlSessionFactory sourceSqlsessionFactory(@Qualifier("source2DataSource") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/mapper2/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "source2TransactionManager")
    @Primary
    public DataSourceTransactionManager sourceTransactionManager(@Qualifier("source2DataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "source2SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sourceSqlsessionTemplate(@Qualifier("source2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws  Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}*/
