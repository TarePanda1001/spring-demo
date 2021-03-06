package com.wyl.batchdemo.springbatch.config;

import com.wyl.batchdemo.springbatch.listener.JobListener;
import com.wyl.batchdemo.springbatch.model.Access;
import com.wyl.batchdemo.springbatch.processor.MyProcessor;
import com.wyl.batchdemo.springbatch.reader.MybatisReader;
import com.wyl.batchdemo.springbatch.writer.MyWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.orm.JpaNativeQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weiyilin
 * @date 2019/5/9 上午11:55
 */
@Configuration
@EnableBatchProcessing
@Slf4j
public class BatchConfiguration {

    @Resource
    private JobBuilderFactory jobBuilderFactory;    //用于构建JOB

    @Resource
    private StepBuilderFactory stepBuilderFactory;  //用于构建Step

    @Resource
    private EntityManagerFactory emf;           //注入实例化Factory 访问数据

    @Resource
    private JobListener jobListener;            //简单的JOB listener

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 一个简单基础的Job通常由一个或者多个Step组成
     */
    @Bean
    public Job dataHandleJob() {
        return jobBuilderFactory.get("myJob")
                .incrementer(new RunIdIncrementer())
                .start(handleDataStep()).    //start是JOB执行的第一个step
//                next(xxxStep()).
//                next(xxxStep()).
//                ...
        listener(jobListener).      //设置了一个简单JobListener
                build();
    }

    /**
     * 一个简单基础的Step主要分为三个部分
     * ItemReader : 用于读取数据
     * ItemProcessor : 用于处理数据
     * ItemWriter : 用于写数据
     */
    @Bean
    public Step handleDataStep() {
        return stepBuilderFactory.get("getData")
                // <输入,输出> 。chunk通俗的讲类似于SQL的commit; 这里表示处理(processor)100条后写入(writer)一次。
                .<Access, Access>chunk(100)
                .faultTolerant()
                .retryLimit(3)
                //捕捉到异常就重试,重试100次还是异常,JOB就停止并标志失败
                .retry(Exception.class).skipLimit(100)
                .skip(Exception.class)
                //指定ItemReader
//                .reader(new JpaReader(emf))
                .reader(new MybatisReader(sqlSessionFactory))
                //指定ItemProcessor
                .processor(new MyProcessor())
                //指定ItemWriter
                .writer(new MyWriter())
                .build();
    }

    @Bean
    public ItemReader<? extends Access> getDataReader() {
        //读取数据,这里可以用JPA,JDBC,JMS 等方式 读入数据
        JpaPagingItemReader<Access> reader = new JpaPagingItemReader<>();
        //这里选择JPA方式读数据 一个简单的 native SQL
        String sqlQuery = "SELECT * FROM access";
        try {
            JpaNativeQueryProvider<Access> queryProvider = new JpaNativeQueryProvider<>();
            queryProvider.setSqlQuery(sqlQuery);
            queryProvider.setEntityClass(Access.class);
            queryProvider.afterPropertiesSet();
            reader.setEntityManagerFactory(emf);
            reader.setPageSize(3);
            reader.setQueryProvider(queryProvider);
            reader.afterPropertiesSet();
            //所有ItemReader和ItemWriter实现都会在ExecutionContext提交之前将其当前状态存储在其中,如果不希望这样做,可以设置setSaveState(false)
            reader.setSaveState(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reader;
    }

    @Bean
    public ItemProcessor<Access, Access> getDataProcessor() {
        return new ItemProcessor<Access, Access>() {
            @Override
            public Access process(Access access) throws Exception {
                log.info("processor data : " + access.toString());  //模拟  假装处理数据,这里处理就是打印一下
                return access;
            }
        };
//        lambda也可以写为:
//        return access -> {
//            log.info("processor data : " + access.toString());
//            return access;
//        };
    }

    @Bean
    public ItemWriter<Access> getDataWriter() {
        return list -> {
            for (Access access : list) {
                log.info("write data : " + access); //模拟 假装写数据 ,这里写真正写入数据的逻辑
            }
        };
    }

//    @Bean
//    public MyBatisPagingItemReader<Access> mybaticReader() {
//        MyBatisPagingItemReader<Access> myBatisPagingItemReader = new MyBatisPagingItemReader<>();
//        myBatisPagingItemReader.setSqlSessionFactory(sqlSessionFactory);
//        myBatisPagingItemReader.setQueryId("com.wyl.batchdemo.springbatch.mapper.AccessMapper.listAccess");
//        myBatisPagingItemReader.setPageSize(500);
//        Map<String, Object> map = new HashMap<>();
//        map.put("start", 100);
//        map.put("end", 400);
//        myBatisPagingItemReader.setParameterValues(map);
//        return myBatisPagingItemReader;
//    }

}
