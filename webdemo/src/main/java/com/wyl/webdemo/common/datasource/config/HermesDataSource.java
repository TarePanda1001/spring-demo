package com.wyl.webdemo.common.datasource.config;


import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.wyl.webdemo.mapper.hermes", sqlSessionTemplateRef = "hermesSqlSessionTemplate")
public class HermesDataSource {
    @Bean(name = "hermesData")
    @ConfigurationProperties(prefix = "spring.hermes.datasource.hikari")
    public DataSource hermesData(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "hermesSqlSessionFactory")
    public SqlSessionFactory hermesSqlSessionFactory(@Qualifier("hermesData") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/hermes/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "hermesTransactionManager")
    public DataSourceTransactionManager hermesTransactionManager(@Qualifier("hermesData") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "hermesSqlSessionTemplate")
    public SqlSessionTemplate hermesSqlSessionTemplate(@Qualifier("hermesSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
