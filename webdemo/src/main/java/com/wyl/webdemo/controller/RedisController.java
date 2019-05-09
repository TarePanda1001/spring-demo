package com.wyl.webdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/set", method = RequestMethod.GET)
    public String testRedis(String key, String value){
        if(redisTemplate.hasKey(key)){
            return "get key:" + key + " -> value:" + Optional.ofNullable(redisTemplate.opsForValue().get(key)).orElse("null");
        }
        redisTemplate.opsForValue().set(key, value, 20l, TimeUnit.SECONDS);
        return "set " + key + " to redis!";
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List testRedis(){
        redisTemplate.opsForList().rightPush("op", "1");
        redisTemplate.opsForList().rightPush("op", "2");
        redisTemplate.opsForList().rightPush("op", "3");
        return redisTemplate.opsForList().range("op", 0,-1);
    }

}
