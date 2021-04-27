package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
/*
 * 测试使用
 * @Scope 默认是单例模式（singlton），可改为多例模式(prototype),加载具体的类上
 */
@Scope("prototype")
@Service
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;

    public AlphaService(){
        System.out.println("实例化AlphaService");
    }

    // 在创建之后调用
    @PostConstruct
    public void init(){
        System.out.println("初始化AlphaService");
    }

    // 在销毁之前调用
    @PreDestroy
    public void destory(){
        System.out.println("销毁AlphaService");
    }

    public String find(){
        return alphaDao.select();
    }
}
