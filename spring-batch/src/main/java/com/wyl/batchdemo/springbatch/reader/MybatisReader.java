package com.wyl.batchdemo.springbatch.reader;

import com.wyl.batchdemo.springbatch.model.Access;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weiyilin
 * @date 2019/5/9 下午3:08
 */
public class MybatisReader extends MyBatisPagingItemReader<Access> {

    private SqlSessionFactory sqlSessionFactory;

    public MybatisReader() {

    }

    public MybatisReader(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        getDataReader();
    }

    public MyBatisPagingItemReader<Access> getDataReader() {
        this.setSqlSessionFactory(sqlSessionFactory);
        this.setQueryId("com.wyl.batchdemo.springbatch.mapper.AccessMapper.listAccess");
        this.setPageSize(500);
        Map<String, Object> map = new HashMap<>();
        map.put("start", 100);
        map.put("end", 400);
        this.setParameterValues(map);
        try {
            this.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

}
