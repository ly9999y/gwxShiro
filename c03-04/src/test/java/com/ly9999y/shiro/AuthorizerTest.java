package com.ly9999y.shiro;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class AuthorizerTest extends BaseTest{

    @Test
    public void testIsPermitted(){
        login("classpath:shiro-authorizer.ini", "zhang", "123");

//        //判断拥有权限：user:create
//        Assert.assertTrue(subject().isPermitted("user:create"));
//        //判断拥有权限：user:update and user:delete
//        Assert.assertTrue(subject().isPermittedAll("user:update", "user:delete"));
//        //判断没有权限：user:view
//        Assert.assertFalse(subject().isPermitted("user:view"));

        //通过二进制位的方式表示权限
        Assert.assertTrue(subject().isPermitted("+user1+2"));//新增权限
        Assert.assertTrue(subject().isPermitted("+user1+8"));//查看权限
        Assert.assertTrue(subject().isPermitted("+user2+10"));//新增及查看

        Assert.assertFalse(subject().isPermitted("+user1+4"));//没有删除权限

        Assert.assertTrue(subject().isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限
    }
}
