package com.wyl.webdemo;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wyl.webdemo.entity.local.StudentInfo;
import com.wyl.webdemo.mapper.hermes.HermesTableMapper;
import com.wyl.webdemo.mapper.local.LocalTableMapper;
import com.wyl.webdemo.mapper.prod.ProdTableMapper;
import com.wyl.webdemo.mapper.service.ServiceTableMapper;
import com.wyl.webdemo.mapper.tianxiao.TianXiaoTableMapper;
import com.wyl.webdemo.service.local.StudentInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WebdemoApplicationTests {
	@Autowired
	private StudentInfoService infoService;

	@Resource
	private LocalTableMapper localTableMapper;

	@Resource
	private ProdTableMapper prodTableMapper;

	@Resource
	private HermesTableMapper hermesTableMapper;

	@Resource
	private TianXiaoTableMapper tianXiaoTableMapper;

	@Resource
	private ServiceTableMapper serviceTableMapper;

	@Test
	public void test(){
		Map<String, Object> map = new HashMap<>();
		map.put("pass_word", "222");
		List<StudentInfo> studentInfo = infoService.selectByMap(map);
		List<StudentInfo> studentInfo1 = infoService.selectPage(new Page<StudentInfo>(1,2), new EntityWrapper<StudentInfo>().eq("age", 12)
		.eq("pass_word", "222")).getRecords();
		log.info("studentInfo1 list is {}", studentInfo1);
		System.out.println(studentInfo1.toString());
	}

	@Test
	public void test1(){
		List<Map> locallist = localTableMapper.allTables();
		List<Map> prod = prodTableMapper.allTables();
		List<Map> hermes = hermesTableMapper.allTables();
		List<Map> tianxiao = tianXiaoTableMapper.allTables();
		List<Map> service = serviceTableMapper.allTables();
		int a = 1;
	}

}
