package com.wyl.webdemo.service.local;

import com.wyl.webdemo.dto.ColumnDto;
import com.wyl.webdemo.dto.TableDescription;

import java.util.List;
import java.util.Map;

public interface TableService {

    List<TableDescription> getTableListBySchema(String cluster,String schema) throws Exception;

    Map<String, List<ColumnDto>> getTableInfo(String cluster, String name) throws Exception;

}
