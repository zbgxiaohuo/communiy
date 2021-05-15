package com.nowcoder.communiy.communiy;

import com.nowcoder.community.CommunityApplication;
import com.nowcoder.community.utils.SensitiveFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/*
* 测试敏感词过滤
* */
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class SensitiveTest {

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitiveFilter(){
        String text = "这里可以赌博，嫖娼，可以吸毒，可以开票，嘻哈嘻哈！";
        text = sensitiveFilter.filter(text);
        System.out.println(text);

        String text2 = "这里可以@赌@博@，嫖@娼，可以吸￥毒，可以开！票，嘻哈嘻哈！";
        text2 = sensitiveFilter.filter(text2);
        System.out.println(text2);
    }
}
