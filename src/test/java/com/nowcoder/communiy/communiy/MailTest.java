package com.nowcoder.communiy.communiy;

import com.nowcoder.community.CommunityApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/*
 * 测试邮件发送
 *
 * */
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTest {

    @Autowired
    private com.nowcoder.community.utils.MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testMail(){
        mailClient.sendMail("1569148939@qq.com", "TEST", "晚上十点记得理发");
    }

    @Test
    public void testHtmlMail(){
        Context context = new Context();
        context.setVariable("username","sunday");

        String content = templateEngine.process("mail/demo", context);
        System.out.println(content);

        mailClient.sendMail("1569148939@qq.com", "HTML", content);
    }

}
