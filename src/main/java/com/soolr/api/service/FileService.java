package com.soolr.api.service;

import com.soolr.api.base.ApiBaseService;
import com.soolr.api.common.vo.PageVo;
import com.soolr.api.common.vo.SearchVo;
import com.soolr.api.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 文件管理接口
 * @author Soolr
 */
public interface FileService extends ApiBaseService<File,String> {

    /**
     * 多条件获取列表
     * @param file
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<File> findByCondition(File file, SearchVo searchVo, Pageable pageable);
}