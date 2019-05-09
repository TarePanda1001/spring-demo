package com.wyl.webdemo.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class MpGenerator {

    @Test
    public void generateCode() {
        generateByTables("local", "op_log");
    }

    private void generateByTables(String cluster,String... tables) {
        if(tables.length == 0)
            return;
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("src/main/java");
        gc.setFileOverride(true);
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("wyl");// 作者

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        // local
        if(cluster.equals("local")){
            dsc.setUsername("root");
            dsc.setPassword("872287583");
            dsc.setUrl("jdbc:mysql://127.0.0.1:3306/example");
        }else if(cluster.equals("prod")){
            // prod
            dsc.setUsername("chaikuprod");
            dsc.setPassword("testchaiku");
            dsc.setUrl("jdbc:mysql://172.21.139.13:3307/yunying");
        }else if(cluster.equals("tianxiao")){
            // tianxiao
            dsc.setUsername("chaikutianxiao");
            dsc.setPassword("testchaiku");
            dsc.setUrl("jdbc:mysql://172.21.139.13:3312/tx");
        }else if(cluster.equals("hermes")){
            // hermes
            dsc.setUsername("chaikuhermes");
            dsc.setPassword("testchaiku");
            dsc.setUrl("jdbc:mysql://172.21.139.13:3308/tts");
        }else if(cluster.equals("service")) {
            // service
            dsc.setUsername("chaikuservice");
            dsc.setPassword("testchaiku");
            dsc.setUrl("jdbc:mysql://172.21.139.13:3306/yunying");
        }else
            return;

        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
//        strategy.setTablePrefix(new String[] { "student_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(tables); // 需要生成的表

        strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);
        strategy.setSuperMapperClass(null);

        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.wyl.webdemo");
        pc.setController("controller");
        pc.setService("service." + cluster);
        pc.setServiceImpl("serviceImpl." + cluster);
        pc.setMapper("mapper." + cluster);
        pc.setEntity("entity." + cluster);
        pc.setXml("xml." + cluster);
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();

    }
}