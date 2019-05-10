package com.wyl.batchdemo.springbatch.mapper;

import com.wyl.batchdemo.springbatch.model.Access;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author weiyilin
 * @date 2019/5/9 下午4:21
 */
@Component
public interface AccessMapper {

    List<Access> listAccess();

}
