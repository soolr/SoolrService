package com.soolr.api.common.utils;

import com.soolr.api.common.constant.CommonConstant;
import com.soolr.api.common.constant.SecurityConstant;
import com.soolr.api.entity.Permission;
import com.soolr.api.entity.Role;
import com.soolr.api.entity.User;
import com.soolr.api.service.UserService;
import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import com.soolr.api.common.constant.CommonConstant;
import com.soolr.api.common.constant.SecurityConstant;
import com.soolr.api.entity.Permission;
import com.soolr.api.entity.Role;
import com.soolr.api.entity.User;
import com.soolr.api.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Soolr
 */
@Component
public class SecurityUtil {

    @Value("${api.saveLoginTime}")
    private Integer saveLoginTime;

    @Value("${api.tokenExpireTime}")
    private Integer tokenExpireTime;

    @Autowired
    private UserService userService;

    public String getToken(String username, Boolean saveLogin){

        if(saveLogin==null||saveLogin){
            tokenExpireTime = saveLoginTime * 60 * 24;
        }
        // 已绑定
        User u = userService.findByUsername(username);
        List<String> authorities = new ArrayList<>();
        for(Permission p : u.getPermissions()){
            if(CommonConstant.PERMISSION_OPERATION.equals(p.getType())
                    && StrUtil.isNotBlank(p.getTitle())
                    && StrUtil.isNotBlank(p.getPath())) {
                authorities.add(p.getTitle());
            }
        }
        for(Role r : u.getRoles()){
            authorities.add(r.getName());
        }
        //登陆成功生成JWT
        String token = Jwts.builder()
                //主题 放入用户名
                .setSubject(u.getUsername())
                //自定义属性 放入用户拥有请求权限
                .claim(SecurityConstant.AUTHORITIES, new Gson().toJson(authorities))
                //失效时间
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpireTime * 60 * 1000))
                //签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.JWT_SIGN_KEY)
                .compact();
        token = SecurityConstant.TOKEN_SPLIT + token;
        return token;
    }
}
