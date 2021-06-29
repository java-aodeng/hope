package com.nihaojewelry.admin.shiro.service.impl;

import cn.hutool.core.util.StrUtil;
import com.nihaojewelry.admin.shiro.realm.ShiroRealm;
import com.nihaojewelry.admin.shiro.service.ShiroService;
import com.nihaojewelry.common.utils.SpringContextHolder;
import com.nihaojewelry.entity.Admin;
import com.nihaojewelry.entity.SysResourceApi;
import com.nihaojewelry.service.AdminService;
import com.nihaojewelry.service.SysResourceApiService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:aodeng(低调小熊猫)
 * @blog:（https://aodeng.cc)
 * @Description: TODO
 * @Date: 19-5-4
 **/
@Service
public class ShiroServiceImpl implements ShiroService {

    private static final Logger log = LoggerFactory.getLogger(ShiroServiceImpl.class);

    @Autowired
    private SysResourceApiService sysResourceApiService;
    @Autowired
    private AdminService adminService;


    @Override
    public Map<String, String> loadFilterChainDefinitions() {


        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        //开放资源文件

        //配置过滤器
         filterChainDefinitionMap.put("/logout", "logout");
         filterChainDefinitionMap.put("/auth/login", "anon");

        //开发环境开放
//        filterChainDefinitionMap.put("/login2","anon");

        //开放druid
        filterChainDefinitionMap.put("/druid/**", "anon");

        //加载数据库中配置的资源权限列表
        List<SysResourceApi> resourcesList = sysResourceApiService.listUrlAndPermission();
        int a = 0;
        for (SysResourceApi resource : resourcesList) {
            if (StrUtil.isNotBlank(resource.getUrl()) && StrUtil.isNotBlank(resource.getPermission())) {
                String permission = "perms[" + resource.getPermission() + "]";
                filterChainDefinitionMap.put(resource.getUrl(), permission);
                a += 1;
            }
        }

        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问,这里我使用user操作即可，如果安全要求比较高，建议使用authc
        filterChainDefinitionMap.put("/**", "user");

        log.info("[ant初始化资源成功,数据库资源条数]-[{}],初始化权限api资源条数-[{}]", resourcesList.size(), a);
        return filterChainDefinitionMap;
    }


    @Override
    public void updatePermission() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = SpringContextHolder.getBean(ShiroFilterFactoryBean.class);
        synchronized (shiroFilterFactoryBean) {
            AbstractShiroFilter abstractShiroFilter = null;
            try {
                abstractShiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new RuntimeException("Get AbstractShiroFilter error");
            }
            PathMatchingFilterChainResolver pathMatchingFilterChainResolver = (PathMatchingFilterChainResolver) abstractShiroFilter.getFilterChainResolver();
            DefaultFilterChainManager defaultFilterChainManager = (DefaultFilterChainManager) pathMatchingFilterChainResolver.getFilterChainManager();
            //清空老的权限控制
            defaultFilterChainManager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
            //重新构建生成
            Map<String, String> map = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> stringEntry : map.entrySet()) {
                String url = stringEntry.getKey();
                String chainDefinition = stringEntry.getValue().trim().replace(" ", "");
                defaultFilterChainManager.createChain(url, chainDefinition);
            }
        }
        log.info("[ant权限重新加载成功]");
    }


    @Override
    public void reloadAuthorizingByUserId(Admin user) {
        RealmSecurityManager realmSecurityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        ShiroRealm shiroReam = (ShiroRealm) realmSecurityManager.getRealms().iterator().next();
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        SimplePrincipalCollection simplePrincipalCollection = new SimplePrincipalCollection(user, realmName);
        subject.runAs(simplePrincipalCollection);
        shiroReam.getAuthorizationCache().remove(subject.getPrincipals());
        subject.releaseRunAs();
        log.info("[以下用户权限更新成功！]-[{}]", user.getName());
    }


    @Override
    public void reloadAuthorizingByRoleId(Integer roleId) {
        List<Admin> userList = adminService.listUsersByRoleId(roleId);
        if (CollectionUtils.isEmpty(userList)) {
            return;
        }
        for (Admin user : userList) {
            reloadAuthorizingByUserId(user);
        }
    }
}
