package com.soolr.api.serviceimpl;

import com.soolr.api.dao.QuartzJobDao;
import com.soolr.api.entity.QuartzJob;
import com.soolr.api.service.QuartzJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务接口实现
 * @author Soolr
 */
@Slf4j
@Service
@Transactional
public class QuartzJobServiceImpl implements QuartzJobService {

    @Autowired
    private QuartzJobDao quartzJobDao;

    @Override
    public QuartzJobDao getRepository() {
        return quartzJobDao;
    }

    @Override
    public List<QuartzJob> findByJobClassName(String jobClassName) {

        return quartzJobDao.findByJobClassName(jobClassName);
    }
}