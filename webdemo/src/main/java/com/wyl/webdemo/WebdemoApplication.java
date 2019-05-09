package com.wyl.webdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.Mapping;

import javax.annotation.ManagedBean;

@MapperScan("com.wyl.webdemo.mapper")
@SpringBootApplication
@EnableCaching
public class WebdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebdemoApplication.class, args);
	}
}
