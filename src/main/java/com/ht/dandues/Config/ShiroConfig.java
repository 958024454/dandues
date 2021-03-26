package com.ht.dandues.Config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig{

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager manager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setLoginUrl("/");
        bean.setSecurityManager(manager);
        Map<String,String> filterMap=new LinkedHashMap<>();
        filterMap.put("/details","authc");
        filterMap.put("/now","authc");
        filterMap.put("/dandues","authc");
        filterMap.put("/admin/*","authc");
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("getUserRealm") UserRealm ur){
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(ur);
        return manager;
    }
    @Bean
    public UserRealm getUserRealm(){
        return new UserRealm();
    }
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 注入session的管理
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 去掉shiro登录时url里的JSESSIONID
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

}
