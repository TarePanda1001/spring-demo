package com.wyl.webdemo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 天校帐号信息表 前端控制器
 * </p>
 *
 * @author wyl123
 * @since 2018-11-02
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping(value = "/{path}")
    public String login(@PathVariable("path") String path){
        log.info("path={}", path);
        return "redirect:" + "http://localhost:8080/studentInfo/student?id=10";
    }

}

