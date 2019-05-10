package com.wyl.batchdemo.springbatch.reader;

import com.wyl.batchdemo.springbatch.model.Access;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;

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
        return this;
    }

}
