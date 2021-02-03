package com.ly9999y.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class PermissionTest {

    public void testIsPermitted(){
        Subject subject = SecurityUtils.getSubject();

        //单个权限
        subject.checkPermission(" system:user:update");
        //多个权限
        subject.checkPermissions("system:user:update", "system:user:delete");
        //多个权限缩写
        // 通过“system:user:update,delete”验证"system:user:update, system:user:delete"是没问题的，
        // 但是反过来是规则不成立
        subject.checkPermissions("system:user:update,delete");
        //单个资源全部权限
        subject.checkPermissions("system:user:create,delete,update:view");
        // 单个资源全部权限缩写
        subject.checkPermissions("system:user:*");
        subject.checkPermissions("system:user");
        // 所有资源权限
        /*
        用户拥有所有资源的“view”所有权限。假设判断的权限是“"system:user:view”，那么需
        要“role5=*:*:view”这样写才行。
         */
        subject.checkPermissions("user:view");


        // 单个实例单个权限
        subject.checkPermissions("user:view:1");
        // 单个实例多个权限
        subject.checkPermissions("user:delete,update:1");
        subject.checkPermissions("user:update:1", "user:delete:1");
        // 单个实例多个权限
        subject.checkPermissions("user:update:1", "user:delete:1", "user:view:1");

        //所有实例单个权限
        subject.checkPermissions("user:auth:1", "user:auth:2");
        // 所有实例单个权限
        subject.checkPermissions("user:view:1", "user:auth:2");

        //WildcardPermission 通配符权限
        /**
         * 等价
         */
        subject.checkPermission("menu:view:1");
        subject.checkPermission(new WildcardPermission("menu:view:1"));
    }

}
