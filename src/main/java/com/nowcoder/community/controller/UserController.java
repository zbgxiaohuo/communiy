package com.nowcoder.community.controller;

import com.nowcoder.community.annotation.LoginRequired;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.utils.CommunityUtil;
import com.nowcoder.community.utils.HostHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/*
* 用户设置（更改头像）
* */
@Controller
@RequestMapping("/user")
public class UserController {

    // 进行日志记录
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // 文件上传之后的存储位置（本地访问地址）
    @Value("${community.path.upload}")
    private String uploadPath;

    // 本机服务器地址（外部访问地址）
    @Value("${community.path.domain}")
    private String domain;

    // 项目名
    @Value("${server.servlet.context-path}")
    private String contextPath;

    // 从里面获得是哪一个用户
    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserService userService;

    // 跳转到用户设置页面
    @LoginRequired
    @RequestMapping(path = "/setting", method = RequestMethod.GET)
    public String getSettingPage(){
        return "/site/setting";
    }

    // 上传头像
    @LoginRequired
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String updateHeader(MultipartFile headerImage, Model model){
        if(headerImage == null){
            model.addAttribute("error", "您还未选择图片");
            return "/site/setting";
        }

        String fileName = headerImage.getOriginalFilename();
        // 获取文件后缀名
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if(StringUtils.isBlank(suffix)){
            model.addAttribute("error", "文件的格式不正确");
            return "/site/setting";
        }

        // 生成随机文件名
        fileName = CommunityUtil.generateUUID() + suffix;
        File dest = new File(uploadPath + "/" + fileName);
        try {
            // 将头像文件存储在这个地址中
            headerImage.transferTo(dest);
        } catch (IOException e) {
            logger.error("上传文件失败" + e.getMessage());
            throw new RuntimeException("上传文件失败，服务器发生异常！", e);
        }

        // 更新当前用户的头像的路径（web访问路径）
        // http://localhost:8080/community/user/header/xxx.png
        User user = hostHolder.getUser();
        String headerUrl = domain +"/" + contextPath + "/user/header/" + fileName;
        userService.updateHeader(user.getId(), headerUrl);

        return "redirect:/index";
    }

    @RequestMapping(path = "/header/{filename}", method = RequestMethod.GET)
    public void getHeader(@PathVariable("filename") String filename, HttpServletResponse response){
        // 服务器存放位置
        filename = uploadPath + "/" + filename;
        // 文件后缀
        String suffix = filename.substring(filename.lastIndexOf("."));
        // 响应图片
        response.setContentType("image/" + suffix);
        try (
                FileInputStream fis = new FileInputStream(filename);
                OutputStream os = response.getOutputStream();
        ) {
            byte[] buffer = new byte[1024];
            int b = 0;
            while((b = fis.read(buffer)) != -1){
                os.write(buffer, 0 , b);
            }
        }catch (Exception e){
            logger.error("读取头像失败", e.getMessage());
        }
    }

    /*
    * 更改密码
    * */
    @RequestMapping(path = "/updatePassword", method = RequestMethod.POST)
    public String updatePassword(String oldPassword, String newPassword1, String newPassword2, Model model){
//        if(oldPassword == null){
//            model.addAttribute("oldPasswordError", "密码不可以为空");
//            return "/site/setting";
//        }
//        if(newPassword1 == null){
//            model.addAttribute("newPasswordError", "密码不可以为空");
//            return "/site/setting";
//        }
//        if(newPassword2 == null){
//            model.addAttribute("passwordError", "密码不可以为空");
//            return "/site/setting";
//        }
        User user = hostHolder.getUser();
        String op = CommunityUtil.md5(oldPassword + user.getSalt());
        if(!op.equals(user.getPassword())){
            model.addAttribute("oldPasswordError", "密码错误");
            return "/site/setting";
        }
        if(newPassword1.length() > 12 || newPassword1.length() < 3){
            model.addAttribute("newPasswordError", "密码长度不正确");
            return "/site/setting";
        }
        // 密码加密
        String np1 = CommunityUtil.md5(newPassword1 + user.getSalt());
        String np2 = CommunityUtil.md5(newPassword2 + user.getSalt());
        if(!np1.equals(np2)){
            model.addAttribute("passwordError", "两次输入的密码不一致！");
            return "/site/setting";
        }

        // 进行更新
        userService.updatePassword(user.getId(), np1);
        return "redirect:/index";
    }
}
