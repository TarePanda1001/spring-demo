package com.wyl.webdemo.serviceImpl.prod;

import com.wyl.webdemo.entity.prod.TxAccount;
import com.wyl.webdemo.mapper.prod.TxAccountMapper;
import com.wyl.webdemo.service.prod.TxAccountService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 天校帐号信息表 服务实现类
 * </p>
 *
 * @author wyl123
 * @since 2018-11-02
 */
@Service
public class TxAccountServiceImpl extends ServiceImpl<TxAccountMapper, TxAccount> implements TxAccountService {

}
