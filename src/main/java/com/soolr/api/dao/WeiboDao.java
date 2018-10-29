package com.soolr.api.dao;

import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.social.Weibo;
import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.social.Weibo;

/**
 * 微博登录数据处理层
 * @author Soolr
 */
public interface WeiboDao extends ApiBaseDao<Weibo,String> {

    /**
     * 通过openId获取
     * @param openId
     * @return
     */
    Weibo findByOpenId(String openId);

    /**
     * 通过username获取
     * @param username
     * @return
     */
    Weibo findByRelateUsername(String username);
}