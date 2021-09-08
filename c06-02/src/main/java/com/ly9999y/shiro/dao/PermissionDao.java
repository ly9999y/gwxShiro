package com.ly9999y.shiro.dao;

import com.ly9999y.shiro.entity.Permission;

public interface PermissionDao {

    public Permission createPermission(Permission permission);

    public void deletePermission(Long permissionId);

}
