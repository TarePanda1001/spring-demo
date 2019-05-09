package com.wyl.webdemo.aspect.rabbitmq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author bjhl
 * 开启异步事件的支持
 */
@Configuration
@EnableAsync
public class AsyncConfig {
    /*
    此处成员变量应该使用@Value从配置中读取
     */
    private static Integer corePoolSize = 10;
    private static Integer maxPoolSize = 200;
    private static Integer queueCapacity = 10;

    @Bean
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.initialize();
        return executor;
    }
}
