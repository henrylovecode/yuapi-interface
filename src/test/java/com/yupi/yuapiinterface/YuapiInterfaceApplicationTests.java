package com.yupi.yuapiinterface;

import com.yupi.yupiclientsdk.client.YuApiClient;
import com.yupi.yupiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class YuapiInterfaceApplicationTests {

    @Resource
    private YuApiClient yuApiClient;


    @Test
    void contextLoads() {
        String result = yuApiClient.getNameByGet("admin");
        User user = new User();
        user.setUserName("henry6666");
        String userNameByPost = yuApiClient.getUserNameByPost(user);
        System.out.println(result);
        System.out.println(userNameByPost);
    }

}
