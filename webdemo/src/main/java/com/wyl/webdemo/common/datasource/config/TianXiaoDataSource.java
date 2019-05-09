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
@MapperScan(basePackages = "com.wyl.webdemo.mapper.tianxiao", sqlSessionTemplateRef = "tianxiaoSqlSessionTemplate")
public class TianXiaoDataSource {
    @Bean(name = "tianxiaoData")
    @ConfigurationProperties(prefix = "spring.tianxiao.datasource.hikari")
    public DataSource tianxiaoData(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "tianxiaoSqlSessionFactory")
    public SqlSessionFactory tianxiaoSqlSessionFactory(@Qualifier("tianxiaoData") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/tianxiao/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "tianxiaoTransactionManager")
    public DataSourceTransactionManager tianxiaoTransactionManager(@Qualifier("tianxiaoData") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "tianxiaoSqlSessionTemplate")
    public SqlSessionTemplate tianxiaoSqlSessionTemplate(@Qualifier("tianxiaoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
