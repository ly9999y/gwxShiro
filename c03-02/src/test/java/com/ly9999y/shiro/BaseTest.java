package com.ly9999y.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;

public class BaseTest {

    public void tearDown(){
        ThreadContext.unbindSubject();
    }

    public void login(String configFile, String username, String password){
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory(configFile);
        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);

        subject.login(usernamePasswordToken);
    }

    public Subject subject() {
        return SecurityUtils.getSubject();
    }
}
