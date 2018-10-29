package com.soolr.api.dao;

import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.QuartzJob;
import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.QuartzJob;

import java.util.List;

/**
 * 定时任务数据处理层
 * @author Soolr
 */
public interface QuartzJobDao extends ApiBaseDao<QuartzJob,String> {

    /**
     * 通过类名获取
     * @param jobClassName
     * @return
     */
    List<QuartzJob> findByJobClassName(String jobClassName);
}