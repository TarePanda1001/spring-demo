package com.wyl.webdemo.controller;

import com.wyl.webdemo.dto.ColumnDto;
import com.wyl.webdemo.dto.TableDescription;
import com.wyl.webdemo.service.local.TableService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/db")
public class DataBaseController {

    @Resource
    private TableService tableService;

    @RequestMapping(value = "/tableList")
    public Object getTableList(String cluster, String schema){
        try {
            List<TableDescription> res = tableService.getTableListBySchema(cluster, schema);
            if(CollectionUtils.isEmpty(res))
                throw new Exception( "schema："+ schema + "is not exist！");
            return res;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/tableInfo")
    public Object getTableInfo(String cluster, String table){
        try {
            Map<String, List<ColumnDto>> res = tableService.getTableInfo(cluster, table);
            if(res == null)
                throw new Exception( "table："+ table + "is not exist！");
            return res;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
