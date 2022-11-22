package com.hinlok.aircorgi2.controller;

import com.hinlok.aircorgi2.model.PassengerInfo;
import com.hinlok.aircorgi2.model.UserInfo;
import com.hinlok.aircorgi2.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    int loginStatus = 1;

    @RequestMapping("/toLogin")
    public String toLogin(ModelMap map) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        if (session.getAttribute("status") == null) {
            return "login";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping("/login")
    public String login(String userId, String password,ModelMap map) {

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(userId, password);

        Session session = subject.getSession();


        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("SessionId:"+session.getId());
        System.out.println("User info received.");
        System.out.println("UserId:"+userId);



        try {
            subject.login(token);
        } catch (UnknownAccountException i) {
            System.out.println("Login failed:UnknownAccountException.");
            map.addAttribute("loginException","用户名未注册");
            return "login";
        } catch (IncorrectCredentialsException i) {
            System.out.println("Login failed:IncorrectCredentialsException.");
            map.addAttribute("loginException","密码错误，请重试！");
            return "login";
        }
        session.setAttribute("userId",userId);
        System.out.println("Login success!");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        session.setAttribute("status",loginStatus);

            return "redirect:/toquery";
    }

    @RequestMapping("/toRegis")
    public String toRegis() {
        return "regis";
    }

    @RequestMapping("/regis")
    public String regis(UserInfo userInfo,ModelMap map) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        if (userService.findByUserId(userInfo.getUserId()) != null) {
            map.addAttribute("regisException", "用户名已存在,请重试");
            return "regis";
        } else if (userService.findByUserId(userInfo.getUserId()) == null) {
            userService.insert(userInfo);
            UserInfo userInfoRegised = userService.findByUserId(userInfo.getUserId());
            session.setAttribute("userId", userInfoRegised.getUserId());
            UsernamePasswordToken token = new UsernamePasswordToken(userInfoRegised.getUserId(), userInfoRegised.getPassword());
            subject.login(token);
            return "redirect:/";
        }

        return "redirect:/toRegis";

    }

    @RequestMapping("/addPassenger")
    public String addPassenger(PassengerInfo passengerInfo) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        passengerInfo.setUserId(session.getAttribute("userId").toString());
        userService.addPassenger(passengerInfo);

        return "redirect:"+session.getAttribute("formerChoice").toString();

    }

    @RequestMapping("/toResetPassword")
    public String toResetPassword(ModelMap map) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        if (session.getAttribute("userId") != null) {
            map.addAttribute("userId", session.getAttribute("userId"));
            return "passwordReset";
        } else {
            return "/";
        }
    }

    @RequestMapping("/passwordReset")
    public String passwordReset(String password, String newPassword) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        UserInfo userInfo = userService.findByUserId(session.getAttribute("userId").toString());

        if (password.equals(userInfo.getPassword())) {
            userInfo.setPassword(newPassword);
            userService.alterPassword(userInfo);
        } else {
            return "redirect:/toResetPassword";
        }

        return "redirect:/logout";
    }

}
