package com.wyl.webdemo.service.local;

import com.wyl.webdemo.dto.OpDTO;
import com.wyl.webdemo.dto.OpLogVO;
import com.wyl.webdemo.entity.local.OpLog;
import com.baomidou.mybatisplus.service.IService;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyl123
 * @since 2018-12-17
 */
public interface OpLogService extends IService<OpLog> {

    List<OpLogVO> getOpList(Long orgId, Long campusId, OpDTO dto) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException;

}
