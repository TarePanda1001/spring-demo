package com.wyl.webdemo.serviceImpl.local;

import com.wyl.webdemo.common.enums.DataSourceEnums;
import com.wyl.webdemo.common.enums.ColumnAttributeEnums;
import com.wyl.webdemo.common.enums.TableAttributeEnums;
import com.wyl.webdemo.dto.ColumnDto;
import com.wyl.webdemo.dto.SchemaDto;
import com.wyl.webdemo.dto.TableDescription;
import com.wyl.webdemo.mapper.hermes.HermesTableMapper;
import com.wyl.webdemo.mapper.local.LocalTableMapper;
import com.wyl.webdemo.mapper.prod.ProdTableMapper;
import com.wyl.webdemo.mapper.service.ServiceTableMapper;
import com.wyl.webdemo.mapper.tianxiao.TianXiaoTableMapper;
import com.wyl.webdemo.service.local.TableService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TableServiceImpl implements TableService {

    @Resource
    private LocalTableMapper localTableMapper;

    @Resource
    private ProdTableMapper prodTableMapper;

    @Resource
    private HermesTableMapper hermesTableMapper;

    @Resource
    private TianXiaoTableMapper tianXiaoTableMapper;

    @Resource
    private ServiceTableMapper serviceTableMapper;

    @Override
    public List<TableDescription> getTableListBySchema(String cluster, String schema) throws Exception {
        List<Map> tableMaps = new ArrayList<>();
        if(StringUtils.isNotEmpty(cluster)){
            if(DataSourceEnums.LOCAL.getName().equals(cluster))
                tableMaps = localTableMapper.getListTablesByDataBase(schema);
            else if(DataSourceEnums.PROD.getName().equals(cluster))
                tableMaps = prodTableMapper.getListTablesByDataBase(schema);
            else if(DataSourceEnums.TIANXIAO.getName().equals(cluster))
                tableMaps = tianXiaoTableMapper.getListTablesByDataBase(schema);
            else if(DataSourceEnums.HERMES.getName().equals(cluster))
                tableMaps = hermesTableMapper.getListTablesByDataBase(schema);
            else if(DataSourceEnums.SERVICE.getName().equals(cluster))
                tableMaps = serviceTableMapper.getListTablesByDataBase(schema);
            else
                throw new Exception("Cluster："+ cluster + " is not exist！");
        }else {
            tableMaps.addAll(localTableMapper.getListTablesByDataBase(schema));
            tableMaps.addAll(prodTableMapper.getListTablesByDataBase(schema));
            tableMaps.addAll(tianXiaoTableMapper.getListTablesByDataBase(schema));
            tableMaps.addAll(hermesTableMapper.getListTablesByDataBase(schema));
            tableMaps.addAll(serviceTableMapper.getListTablesByDataBase(schema));
        }
        return buildTableAttribute(tableMaps);
    }

    @Override
    public Map<String, List<ColumnDto>> getTableInfo(String cluster, String tableName) throws Exception {
        List<Map> tableMaps = new ArrayList<>();
        if(StringUtils.isNotEmpty(cluster)){
            if(DataSourceEnums.LOCAL.getName().equals(cluster))
                tableMaps = localTableMapper.listTableColumns(tableName);
            else if(DataSourceEnums.PROD.getName().equals(cluster))
                tableMaps = prodTableMapper.listTableColumns(tableName);
            else if(DataSourceEnums.TIANXIAO.getName().equals(cluster))
                tableMaps = tianXiaoTableMapper.listTableColumns(tableName);
            else if(DataSourceEnums.HERMES.getName().equals(cluster))
                tableMaps = hermesTableMapper.listTableColumns(tableName);
            else if(DataSourceEnums.SERVICE.getName().equals(cluster))
                tableMaps = serviceTableMapper.listTableColumns(tableName);
            else
                throw new Exception("Cluster："+ cluster + " is not exist！");
        }else {
            tableMaps.addAll(prodTableMapper.listTableColumns(tableName));
            tableMaps.addAll(tianXiaoTableMapper.listTableColumns(tableName));
            tableMaps.addAll(hermesTableMapper.listTableColumns(tableName));
            tableMaps.addAll(serviceTableMapper.listTableColumns(tableName));
        }
        return buildColumnAttribute(tableMaps);
    }

    private List<TableDescription> buildTableAttribute(List<Map> tableMaps){
        List<TableDescription> res = new ArrayList<>();
        for (Map map : tableMaps){
            TableDescription tableDescription = new TableDescription();
            tableDescription.setTableName(map.get(TableAttributeEnums.TABLE_NAME.getLabel()) != null ? map.get(TableAttributeEnums.TABLE_NAME.getLabel()).toString() : map.get(TableAttributeEnums.TABLE_NAME.getLowerLabel()).toString());
            tableDescription.setTableComment(map.get(TableAttributeEnums.TABLE_COMMENT.getLabel()) != null ? map.get(TableAttributeEnums.TABLE_COMMENT.getLabel()).toString() : map.get(TableAttributeEnums.TABLE_COMMENT.getLowerLabel()).toString());
            res.add(tableDescription);
        }
        return res;
    }

    private Map<String, List<ColumnDto>> buildColumnAttribute(List<Map> tableMaps){
        Map<String, List<ColumnDto>> tableColumnMap= new HashMap<>();
        for(Map map: tableMaps){
            SchemaDto schemaDto = new SchemaDto();
            ColumnDto columnDto = new ColumnDto();
            schemaDto.setTableSchema(map.get(ColumnAttributeEnums.TABLE_SCHEMA.getLabel()) != null ? map.get(ColumnAttributeEnums.TABLE_SCHEMA.getLabel()).toString():map.get(ColumnAttributeEnums.TABLE_SCHEMA.getLowerLabel()).toString());
            schemaDto.setTableName(map.get(ColumnAttributeEnums.TABLE_NAME.getLabel()) != null ? map.get(ColumnAttributeEnums.TABLE_NAME.getLabel()).toString() : map.get(ColumnAttributeEnums.TABLE_NAME.getLowerLabel()).toString());
            columnDto.setColumnName(map.get(ColumnAttributeEnums.COLUMN_NAME.getLabel()) != null ? map.get(ColumnAttributeEnums.COLUMN_NAME.getLabel()).toString() : map.get(ColumnAttributeEnums.COLUMN_NAME.getLowerLabel()).toString());
            columnDto.setColumnType(map.get(ColumnAttributeEnums.COLUMN_TYPE.getLabel()) != null ? map.get(ColumnAttributeEnums.COLUMN_TYPE.getLabel()).toString() : map.get(ColumnAttributeEnums.COLUMN_TYPE.getLowerLabel()).toString());
            columnDto.setColumnKey(map.get(ColumnAttributeEnums.COLUMN_KEY.getLabel()) != null ? map.get(ColumnAttributeEnums.COLUMN_KEY.getLabel()).toString() : map.get(ColumnAttributeEnums.COLUMN_KEY.getLowerLabel()).toString());
            columnDto.setColumnComment(map.get(ColumnAttributeEnums.COLUMN_COMMENT.getLabel()) != null ? map.get(ColumnAttributeEnums.COLUMN_COMMENT.getLabel()).toString() : map.get(ColumnAttributeEnums.COLUMN_COMMENT.getLowerLabel()).toString());
            if(tableColumnMap.containsKey(schemaDto.toString())){
                List<ColumnDto> list = tableColumnMap.get(schemaDto.toString());
                list.add(columnDto);
                tableColumnMap.put(schemaDto.toString(), list);
            }else {
                List<ColumnDto> list = new ArrayList<>();
                list.add(columnDto);
                tableColumnMap.put(schemaDto.toString(), list);
            }
        }

        return tableColumnMap;
    }
}
