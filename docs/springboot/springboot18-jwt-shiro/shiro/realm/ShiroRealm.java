package com.nihaojewelry.admin.shiro.realm;

import cn.hutool.core.util.ObjectUtil;
import com.nihaojewelry.admin.shiro.JwtToken.JWTToken;
import com.nihaojewelry.admin.shiro.JwtToken.JWTUtil;
import com.nihaojewelry.entity.Admin;
import com.nihaojewelry.service.AdminService;
import com.nihaojewelry.service.SysResourceApiService;
import com.nihaojewelry.service.SysRoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author:aodeng(低调小熊猫)
 * @blog:（https://aodeng.cc)
 * @Description: 认证，鉴权
 * @Date: 19-5-4
 **/
@Component
public class ShiroRealm extends AuthorizingRealm {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroRealm.class);

    private final SysResourceApiService sysResourceApiService;
    private final SysRoleService sysRoleService;
    private final AdminService adminService;

    public ShiroRealm(SysResourceApiService sysResourceApiService, SysRoleService sysRoleService, AdminService adminService) {
        this.sysResourceApiService = sysResourceApiService;
        this.sysRoleService = sysRoleService;
        this.adminService = adminService;
    }

    /**
     * @Description: 整合jwt-token必须重新该方法
     * @Param: [token]
     * @return: [token]
     * @Author: aodeng
     * @Date: 2019/4/29
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * @Description: 认证，提供账户信息，返回认证用户的角色信息, 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     * @Param: [principalCollection]
     * @return: [principalCollection]
     * @Author: aodeng
     * @Date: 2019/4/29
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取用户账号 此处有坑，需谨慎
        String token = (String) authenticationToken.getCredentials();

        //解密获得username，用户和数据库进行判断
        String username = JWTUtil.getUsername(token);

        if (null == username || !JWTUtil.verify(token, username)) {
            throw new AuthenticationException("token认证失败！");
        }
        Admin sysuser = adminService.selectUserByName(username);

        if (ObjectUtil.isNull(sysuser)) {
            throw new UnknownAccountException("账号不存在！");
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }

    /**
     * @Description: 授权，为当前登陆的用户授予权限,只有当需要检测用户权限的时候才会调用此方法
     * @Param: [principalCollection]
     * @return: [principalCollection]
     * @Author: aodeng
     * @Date: 2019/4/29
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        if (principalCollection == null) {
            throw new AuthorizationException("principals should not be null");
        }
        //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Set<String> roles = new HashSet<String>();
        Set<String> resources = new HashSet<String>();

        //根据用户id获取角色，资源
        String username = JWTUtil.getUsername(principalCollection.toString());
        Admin sysuser = adminService.selectUserByName(username);

        roles = sysRoleService.findRoleByUserId(sysuser.getId());
        resources = sysResourceApiService.findPermsByUserId(sysuser.getId());

        //设置该用户拥有的角色和权限
        info.setRoles(roles);
        info.setStringPermissions(resources);
        LOGGER.info("[当前登录用户授权完成,用户id]-[{}]", sysuser.getId());
        return info;
    }
}
