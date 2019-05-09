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
@MapperScan(basePackages = "com.wyl.webdemo.mapper.service", sqlSessionTemplateRef = "serviceSqlSessionTemplate")
public class ServiceDataSource {
    @Bean(name = "serviceData")
    @ConfigurationProperties(prefix = "spring.service.datasource.hikari")
    public DataSource serviceData(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "serviceSqlSessionFactory")
    public SqlSessionFactory serviceSqlSessionFactory(@Qualifier("serviceData") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/service/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "serviceTransactionManager")
    public DataSourceTransactionManager serviceTransactionManager(@Qualifier("serviceData") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "serviceSqlSessionTemplate")
    public SqlSessionTemplate serviceSqlSessionTemplate(@Qualifier("serviceSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
