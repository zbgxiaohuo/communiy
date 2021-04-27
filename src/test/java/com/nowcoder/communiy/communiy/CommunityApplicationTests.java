package com.nowcoder.communiy.communiy;

import com.nowcoder.community.CommunityApplication;
import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ContextConfiguration(classes = CommuniyApplication.class):测试时将CommuniyApplication作为配置类
 * implements ApplicationContextAware:实现该接口就可以得到spring容器（需要实现方法）
 */

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {
    // 依赖注入方式（便捷）-------------------------------------------------------------------------------------------------
    // @Autowired--自动获取Bean对象
    // @Qualifier("alp")--Bean默认名是类名的小写，但是也可以自定义,然后@Qualifier("")获取
    @Autowired
    @Qualifier("alp")
    private  AlphaDao alphaDao;

    @Test
    public void testDI(){
        System.out.println(alphaDao.select());
    }

    @Test
    void contextLoads() {
    }
    //------------------------------------------------------------------------------------------------------------------
    // 主动获取Bean方式---了解---方便理解spring底层

    // 用来记录spring容器
    private ApplicationContext applicationContext;

    /**
     * 重写ApplicationContextAware的方法，用以获得容器
     * @param applicationContext BeanFactory（容器顶层接口）的子接口，拥有更多的功能
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 测试容器获取是否成功
     */
    @Test
    public void testApplicationContext(){
        // 检测容器
        System.out.println(applicationContext);
        // 检测从容其中获取Bean
        AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
        // 获取Bean对象
        System.out.println(alphaDao);
        // 得到Bean对象对应的查询结果
        System.out.println(alphaDao.select());

        // Bean默认名是类名的小写，但是也可以自定义
        alphaDao = applicationContext.getBean("alp", AlphaDao.class);
        System.out.println(alphaDao.select());
    }

    /**
     * 测试Bean的管理方式(实例化-前，初始化-中，销毁-后)
     */
    @Test
    public void testBeanManagement() {
        AlphaService alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);

        // 再次获取bean,结果相同，说明---单例模式，只被实例化一次
        alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);
    }

    /**
     * 修改源码中的配置--自定义Config类
     */
    @Test
    public void testBeanConfig(){
        SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
    }
}
