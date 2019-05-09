package com.wyl.webdemo.common.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;

import java.util.Map;

public class MapUtils {

    public static String map2String(Map<String, Object> map){
        if (CollectionUtils.isEmpty(map)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (String key : map.keySet()){
            builder.append(JSON.toJSON(map.get(key)).toString());
            // 只获取一个参数dto的json（约定：controller的参数也应该只有一个dto）
            if(builder.length() > 0){
                break;
            }
        }
        return builder.toString();
    }

}
