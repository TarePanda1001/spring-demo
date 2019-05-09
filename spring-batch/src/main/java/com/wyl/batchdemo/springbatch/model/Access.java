package com.wyl.batchdemo.springbatch.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author weiyilin
 * @date 2019/5/9 上午11:47
 */
@Entity
@Table
@Data
public class Access {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String shopName;
    private String categoryName;
    private String brandName;
    private String shopId;
    private String omit;
    private String updateTime;
    private boolean deleteStatus;
    private String createTime;
    private String description;

}
