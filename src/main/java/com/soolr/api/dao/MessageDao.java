package com.soolr.api.dao;

import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.Message;
import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.Message;

import java.util.List;

/**
 * 消息内容数据处理层
 * @author Soolr
 */
public interface MessageDao extends ApiBaseDao<Message,String> {

    /**
     * 通过创建发送标识获取
     * @param createSend
     * @return
     */
    List<Message> findByCreateSend(Boolean createSend);
}