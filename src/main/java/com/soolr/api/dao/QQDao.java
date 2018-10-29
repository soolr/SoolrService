package com.soolr.api.dao;

import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.social.QQ;
import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.social.QQ;

/**
 * qq登录数据处理层
 * @author Soolr
 */
public interface QQDao extends ApiBaseDao<QQ,String> {

    /**
     * 通过openId获取
     * @param openId
     * @return
     */
    QQ findByOpenId(String openId);

    /**
     * 通过username获取
     * @param username
     * @return
     */
    QQ findByRelateUsername(String username);
}