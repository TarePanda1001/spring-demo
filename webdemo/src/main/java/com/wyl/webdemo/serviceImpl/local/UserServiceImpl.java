package com.wyl.webdemo.serviceImpl.local;

import com.wyl.webdemo.entity.local.User;
import com.wyl.webdemo.mapper.local.UserMapper;
import com.wyl.webdemo.service.local.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyl123
 * @since 2018-11-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
