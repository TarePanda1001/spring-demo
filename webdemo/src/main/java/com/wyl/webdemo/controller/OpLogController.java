package com.wyl.webdemo.controller;


import com.wyl.webdemo.dto.OpDTO;
import com.wyl.webdemo.dto.OpLogVO;
import com.wyl.webdemo.dto.R;
import com.wyl.webdemo.entity.local.OpLog;
import com.wyl.webdemo.service.local.OpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wyl123
 * @since 2018-12-17
 */
@RestController
@RequestMapping("/opLog")
public class OpLogController {

    @Autowired
    private OpLogService opLogService;

    @RequestMapping(value = "/list")
    public R<List<OpLogVO>> getOpList(OpDTO dto) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return new R(opLogService.getOpList(158L, 3794L, dto));
    }


}

