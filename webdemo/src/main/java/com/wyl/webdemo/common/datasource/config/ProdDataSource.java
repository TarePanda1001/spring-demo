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
@MapperScan(basePackages = "com.wyl.webdemo.mapper.prod", sqlSessionTemplateRef = "prodSqlSessionTemplate")
public class ProdDataSource {
    @Bean(name = "prodData")
    @ConfigurationProperties(prefix = "spring.prod.datasource.hikari")
    public DataSource prodData(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "prodSqlSessionFactory")
    public SqlSessionFactory prodSqlSessionFactory(@Qualifier("prodData") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/prod/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "prodTransactionManager")
    public DataSourceTransactionManager prodTransactionManager(@Qualifier("prodData") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "prodSqlSessionTemplate")
    public SqlSessionTemplate prodSqlSessionTemplate(@Qualifier("prodSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
