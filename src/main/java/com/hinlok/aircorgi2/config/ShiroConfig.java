package com.hinlok.aircorgi2.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /*创建ShiroFilterFactoryBean*/
    @Bean(name="shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        /*设置过滤器*/
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        filterMap.put("/updateLogs", "anon");
        filterMap.put("/toRegis", "anon");
        filterMap.put("/regis", "anon");
        filterMap.put("/test", "anon");
        filterMap.put("/css/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/img/**", "anon");
        filterMap.put("/fonts/**", "anon");
        filterMap.put("/templates", "anon");
        filterMap.put("/", "anon");
        filterMap.put("/toquery", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/logout", "logout");
        filterMap.put("/**", "authc");/*通配所有未放行路径均需要认证授权才可访问,！！需放至Map最底端！！*/

        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;

    }

    /*创建DefaultWebSecurityManager*/
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userReaml")UserRealm userRealm) {

        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        defaultWebSecurityManager.setRealm(userRealm);

        return defaultWebSecurityManager;

    }

    /*创建Realm*/
    @Bean(name = "userReaml")
    public UserRealm getRealm() {
        return new UserRealm();
    }

}
