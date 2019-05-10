package com.nihaojewelry.admin.shiro.JwtToken;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author:aodeng(低调小熊猫)
 * @blog:（https://aodeng.cc)
 * @Description: TODO
 * @Date: 19-4-29
 **/
public class JWTToken implements AuthenticationToken {
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
