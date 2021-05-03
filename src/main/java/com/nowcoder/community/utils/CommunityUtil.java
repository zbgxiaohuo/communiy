package com.nowcoder.community.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

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

}
