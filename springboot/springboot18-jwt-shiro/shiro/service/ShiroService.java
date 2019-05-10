package com.nihaojewelry.admin.shiro.service;

import com.nihaojewelry.entity.Admin;
import com.nihaojewelry.entity.SysUser;

import java.util.Map;

/**
 * @author:aodeng(低调小熊猫)
 * @blog:（https://aodeng.cc)
 * @Description: TODO
 * @Date: 19-5-4
 **/
public interface ShiroService {
    /***
     * 初始化权限
     * @return
     */
    Map<String, String> loadFilterChainDefinitions();

    /***
     * 重新加载权限
     */
    void updatePermission();

    /***
     * 重新加载用户权限
     * @param user
     */
    void reloadAuthorizingByUserId(Admin user);

    /***
     * 重新加载所有拥有roleId角色的用户权限
     * @param roleId
     */
    void reloadAuthorizingByRoleId(Integer roleId);
}
