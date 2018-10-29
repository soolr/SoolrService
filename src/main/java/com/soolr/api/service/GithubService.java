package com.soolr.api.service;

import com.soolr.api.base.ApiBaseService;
import com.soolr.api.common.vo.SearchVo;
import com.soolr.api.entity.social.Github;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Github登录接口
 * @author Soolr
 */
public interface GithubService extends ApiBaseService<Github,String> {

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

    /**
     * 分页多条件获取
     * @param username
     * @param relateUsername
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<Github> findByCondition(String username, String relateUsername, SearchVo searchVo, Pageable pageable);
}