package com.wyl.webdemo.test;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author weiyilin
 * @date 2019/5/6 下午5:53
 */
public class StreamTest {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1,2,3,4,5,5,4,3,2,1,3,4);
        AtomicReference<Integer> k = new AtomicReference<>(0);
        list.parallelStream().forEach((i) -> {
            k.getAndSet(k.get() + 1);
            System.out.println(Thread.currentThread().getId() + " : " + k);
        });
    }

}
