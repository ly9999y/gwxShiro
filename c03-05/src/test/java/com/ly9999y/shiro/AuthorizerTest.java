package com.ly9999y.shiro;

import junit.framework.Assert;
import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Test;

import java.util.Arrays;

public class AuthorizerTest extends BaseTest{

    @Test
    public void testIsPermitted(){
        login("classpath:shiro-jdbc-authorizer.ini", "zhang", "123");
        //判断拥有权限：user:create
//        junit.framework.Assert.assertTrue(subject().isPermitted("user1:update"));
//        junit.framework.Assert.assertTrue(subject().isPermitted("user2:update"));
        //通过二进制位的方式表示权限
//        junit.framework.Assert.assertTrue(subject().isPermitted("+user1+2"));//新增权限
//        junit.framework.Assert.assertTrue(subject().isPermitted("+user1+8"));//查看权限
//        junit.framework.Assert.assertTrue(subject().isPermitted("+user2+10"));//新增及查看

        junit.framework.Assert.assertFalse(subject().isPermitted("+user1+4"));//没有删除权限

        Assert.assertTrue(subject().isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限
    }
}
