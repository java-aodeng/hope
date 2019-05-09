package com.hope.shiro.realm;

import com.hope.shiro.model.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**Hope自定义Ream(加强版)
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-29 13:14
 **/
public class HopeShiroReam extends AuthorizingRealm{

    @Autowired(required = false)
    private RedisSessionDAO redisSessionDAO;

    /***
     * 授权，为当前登陆的用户授予权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){

        return null;
    }

    /***
     * 认证，提供账户信息，返回认证用户的角色信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户账号
        String username=(String) token.getPrincipal();
        System.out.println(username+"++++++++++++++");
        System.out.println("token信息："+token.getCredentials());

        //模拟从数据库查询的数据，重点是shiro，为了简单点 SysUser sysuser=sysUserService.getByUserName(username);
        SysUser sysuser=new SysUser();
        sysuser.setUsername("1");
        sysuser.setUsername("admin");
        sysuser.setPassword("872359cc44c637cc73b7cd55c06d95e4");
        sysuser.setSalt("8cd50474d2a3c1e88298e91df8bf6f1c");
        sysuser.setStatus(1);
        if (sysuser == null){
            throw new UnknownAccountException("帐号不存在！");
        }
        if (null != sysuser.getStatus() && 0==sysuser.getStatus()){
            throw new LockedAccountException("账号锁定，禁止登录！");
        }
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(
                sysuser,
                sysuser.getPassword(),
                ByteSource.Util.bytes(sysuser.getCredentialsSalt()),
                getName()
        );
        return authenticationInfo;
    }
}
