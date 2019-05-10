package com.wyl.batchdemo;

import java.util.Arrays;
import java.util.List;

/**
 * @author weiyilin
 * @date 2019/5/10 上午11:13
 */
public class TestForeach {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);

        int sum = list.stream()
                .mapToInt(e -> e)
                .reduce(0, (x, y) -> {
                    if (y >= 4) {
                        x++;
                    }else {
                        System.out.println("-----no add-----");
                    }
                    return x;
                });
        System.out.print(sum);

    }

    }
