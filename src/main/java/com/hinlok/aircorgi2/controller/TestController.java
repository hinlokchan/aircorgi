package com.hinlok.aircorgi2.controller;

import com.hinlok.aircorgi2.model.OrderInfo;
import com.hinlok.aircorgi2.model.UserInfo;
import com.hinlok.aircorgi2.service.OrderService;
import com.hinlok.aircorgi2.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLOutput;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/phone")
    @ResponseBody
    public Object phone() {
        UserInfo userInfo = userService.findByUserId("hinlok1997");
        System.out.println(userInfo.getUserId());
        System.out.println(userInfo.getPhoneNum());
        System.out.println(userInfo.getPassword());
        System.out.println(userInfo.getEmail());
        return userInfo;
    }

    @ResponseBody
    @RequestMapping("/test")
    public String test(UserInfo userInfo) {
        return userInfo.getUserId();
    }

    @ResponseBody
    @RequestMapping("/modal")
    public String modal(String status) {
        System.out.println(status);
        return status;
    }

    @ResponseBody
    @RequestMapping("/dataTest")
    public List<OrderInfo> dataTest() {
        System.out.println("Received.");
        return orderService.queryAllOrder("hinlok1997");
    }

}
