package com.wyl.batchdemo.springbatch.reader;

import com.wyl.batchdemo.springbatch.model.Access;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.orm.JpaNativeQueryProvider;

import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weiyilin
 * @date 2019/5/9 上午11:54
 */
public class JpaReader extends JpaPagingItemReader<Access> {

    private EntityManagerFactory emf;           //注入实例化Factory 访问数据

    public JpaReader() {
    }

    public JpaReader(EntityManagerFactory emf) {
        this.emf = emf;
        getDataReader();
    }

    public ItemReader<? extends Access> getDataReader() {
        //读取数据,这里可以用JPA,JDBC,JMS 等方式 读入数据
        //这里选择JPA方式读数据 一个简单的 native SQL
        String sqlQuery = "SELECT * FROM access";
        try {
            JpaNativeQueryProvider<Access> queryProvider = new JpaNativeQueryProvider<>();
            queryProvider.setSqlQuery(sqlQuery);
            queryProvider.setEntityClass(Access.class);
            queryProvider.afterPropertiesSet();
            this.setEntityManagerFactory(emf);
            this.setPageSize(3);
            this.setQueryProvider(queryProvider);
            this.afterPropertiesSet();
            //所有ItemReader和ItemWriter实现都会在ExecutionContext提交之前将其当前状态存储在其中,如果不希望这样做,可以设置setSaveState(false)
            this.setSaveState(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

}
