package com.hinlok.aircorgi2.config;


import com.hinlok.aircorgi2.model.UserInfo;
import com.hinlok.aircorgi2.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /*执行逻辑授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("执行逻辑授权");

        return null;

    }

    /*执行认证逻辑*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        Subject subject = SecurityUtils.getSubject();

        Session session = subject.getSession();

        System.out.println("Conducting Auth:"+session.getAttribute("userId"));

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        UserInfo userInfo = userService.findByUserId(token.getUsername());

        System.out.println(token.getUsername());

        if (userInfo == null) {
            return null;
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("", userInfo.getPassword(), "");

        return simpleAuthenticationInfo;
    }

}
