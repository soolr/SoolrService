package com.soolr.api.service;

import com.soolr.api.base.ApiBaseService;
import com.soolr.api.common.vo.SearchVo;
import com.soolr.api.entity.social.Weibo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 微博登录接口
 * @author Soolr
 */
public interface WeiboService extends ApiBaseService<Weibo,String> {

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

    /**
     * 分页多条件获取
     * @param username
     * @param relateUsername
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<Weibo> findByCondition(String username, String relateUsername, SearchVo searchVo, Pageable pageable);
}