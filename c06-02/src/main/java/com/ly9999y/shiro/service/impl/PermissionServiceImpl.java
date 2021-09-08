package com.ly9999y.shiro.service.impl;

import com.ly9999y.shiro.dao.PermissionDao;
import com.ly9999y.shiro.dao.impl.PermissionDaoImpl;
import com.ly9999y.shiro.entity.Permission;
import com.ly9999y.shiro.service.PermissionService;


public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
