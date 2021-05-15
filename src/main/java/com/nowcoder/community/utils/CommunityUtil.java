package com.nowcoder.community.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommunityUtil {

    // 生成随机字符串
    public static String generateUUID(){
        // 通常包括字母、数字、和“-”，将“-”进行替换
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    // MD5加密
    // hello => abc123def
    // hello + 3e4a8 => abc123def3e4a8
    public static String md5(String key){
        // 判断是否为空
        if(StringUtils.isBlank(key)) {
            return null;
        }
        // 将字符串加密为十六进制字符串并返回
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    // 封装json
    public static String getJsonString(int code, String msg, Map<String, Object> map){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        if(map != null){
            for(String key : map.keySet()){
                jsonObject.put(key, map.get(key));
            }
        }
        return jsonObject.toString();
    }

    // 重载
    public static String getJsonString(int code, String msg) {
        return getJsonString(code, msg, null);
    }

    // 重载
    public static String getJsonString(int code) {
        return getJsonString(code, null, null);
    }

    // 测试json
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", 25);
        System.out.println(getJsonString(0,"ok",map));
    }
}
