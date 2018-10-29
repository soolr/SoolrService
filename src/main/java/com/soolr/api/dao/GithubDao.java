package com.soolr.api.dao;

import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.social.Github;
import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.social.Github;

/**
 * Github登录数据处理层
 * @author Soolr
 */
public interface GithubDao extends ApiBaseDao<Github,String> {

    /**
     * 通过openId获取
     * @param openId
     * @return
     */
    Github findByOpenId(String openId);

    /**
     * 通过username获取
     * @param username
     * @return
     */
    Github findByRelateUsername(String username);
}