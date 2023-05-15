package com.yupi.yuapiinterface.controller;



import com.yupi.yupiclientsdk.model.User;
import com.yupi.yupiclientsdk.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * FileName:     NameController
 * CreateBy:     IntelliJ IDEA
 * Author:       wei
 * Date:         2023-05-09
 * Description : API
 */
@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/")
    public String getNameByGet(String name){

        return "GET 你的名字是" + name;
    }

    @PostMapping ("/")
    public String getNameByPost(@RequestParam String name){
        return "Post 你的名字是" + name;
    }
    @PostMapping("/user")
    public String getUserNameByPost(@RequestBody User user  , HttpServletRequest request){
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        String body = request.getHeader("body");

        if (!"admin".equals(accessKey)){
            throw new RuntimeException("无权限");
        }
        if (Long.parseLong(nonce) > 1000000){
            throw new RuntimeException("无权限");
        }

        //todo 时间和当前时间不能超过5分钟


        String  serverSign= SignUtils.getSign(body, "abcdefgh");
        if (!sign.equals(serverSign)){
            throw new RuntimeException("无权限");
        }


        return "Post 用户名字是" + user.getUserName();
    }
}
