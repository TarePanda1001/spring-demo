package com.wyl.batchdemo.springbatch.writer;

import com.wyl.batchdemo.springbatch.model.Access;

import org.springframework.batch.item.ItemWriter;
import java.util.List;

/**
 * @author weiyilin
 * @date 2019/5/9 上午11:54
 */
public class MyWriter implements ItemWriter<Access> {

    @Override
    public void write(List<? extends Access> list) throws Exception {
        for (Access access : list) {
            System.out.println("id=" +access.getId() + ", name=" + access.getUsername());
        }
    }
}
