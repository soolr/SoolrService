package com.soolr.api.service;

import com.soolr.api.base.ApiBaseService;
import com.soolr.api.entity.QuartzJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 定时任务接口
 * @author Soolr
 */
public interface QuartzJobService extends ApiBaseService<QuartzJob,String> {

    /**
     * 通过类名获取
     * @param jobClassName
     * @return
     */
    List<QuartzJob> findByJobClassName(String jobClassName);
}