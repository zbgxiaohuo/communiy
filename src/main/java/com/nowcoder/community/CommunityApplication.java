package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


/**
 *  @SpringBootApplication      大致做的事情如下
 *  1、@SpringBootConfiguration 表明是配置类
 *  2、@EnableAutoConfiguration 开启自动配置
 *  3、@ComponentScan           开启组件扫描：扫描配置类所在的包以及子包（Controller、Service、Resposity都是Component实现的，包含这四个注解可以被扫描）
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
// 配置类
public class CommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }
}
