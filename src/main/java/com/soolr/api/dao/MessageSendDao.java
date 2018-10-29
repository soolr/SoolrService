package com.soolr.api.dao;

import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.MessageSend;
import com.soolr.api.base.ApiBaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 消息发送数据处理层
 * @author Soolr
 */
public interface MessageSendDao extends ApiBaseDao<MessageSend,String> {

    /**
     * 通过消息id删除
     * @param messageId
     */
    void deleteByMessageId(String messageId);
}