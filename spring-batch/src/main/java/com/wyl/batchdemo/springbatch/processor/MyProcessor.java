package com.wyl.batchdemo.springbatch.processor;

import com.wyl.batchdemo.springbatch.model.Access;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author weiyilin
 * @date 2019/5/9 上午11:54
 */
public class MyProcessor implements ItemProcessor<Access, Access> {
    @Override
    public Access process(Access item) throws Exception {
        if (item.getId().intValue() % 10 == 0) {
            return item;
        }
        return null;
    }
}
