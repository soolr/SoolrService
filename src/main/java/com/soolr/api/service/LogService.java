package com.soolr.api.service;


import com.soolr.api.base.ApiBaseService;
import com.soolr.api.common.vo.SearchVo;
import com.soolr.api.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 日志接口
 * @author Soolr
 */
public interface LogService extends ApiBaseService<Log,String> {

    /**
     * 日志搜索
     * @param key
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<Log> searchLog(String key, SearchVo searchVo, Pageable pageable);

    /**
     * 删除所有
     */
    void deleteAll();
}
