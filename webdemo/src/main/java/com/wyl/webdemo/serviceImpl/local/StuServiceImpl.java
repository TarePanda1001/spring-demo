package com.wyl.webdemo.serviceImpl.local;

import com.wyl.webdemo.entity.local.Stu;
import com.wyl.webdemo.mapper.local.StuMapper;
import com.wyl.webdemo.service.local.StuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyl123
 * @since 2018-10-08
 */
@Service
public class StuServiceImpl extends ServiceImpl<StuMapper, Stu> implements StuService {

}
