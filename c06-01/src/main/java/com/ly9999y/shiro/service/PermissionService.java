package com.ly9999y.shiro.service;

import com.ly9999y.shiro.entity.Permission;

public interface PermissionService {
    public Permission createPermission(Permission permission);
    public void deletePermission(Long permissionId);
}
