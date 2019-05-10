package com.wyl.batchdemo;

import com.wyl.batchdemo.springbatch.mapper.AccessMapper;
import com.wyl.batchdemo.springbatch.model.Access;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBatchApplicationTests {

    @Resource
    private AccessMapper accessMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
        List<Access> list = accessMapper.listAccess();
        System.out.print(list);
    }

}
