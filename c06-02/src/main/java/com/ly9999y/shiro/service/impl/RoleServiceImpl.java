package com.ly9999y.shiro.service.impl;

import com.ly9999y.shiro.dao.RoleDao;
import com.ly9999y.shiro.dao.impl.RoleDaoImpl;
import com.ly9999y.shiro.entity.Role;
import com.ly9999y.shiro.service.RoleService;

public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao = new RoleDaoImpl();


    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }

    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        roleDao.correlationPermissions(roleId, permissionIds);
    }

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        roleDao.uncorrelationPermissions(roleId, permissionIds);
    }

}
