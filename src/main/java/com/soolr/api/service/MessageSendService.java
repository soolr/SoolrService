package com.soolr.api.service;

import com.soolr.api.base.ApiBaseService;
import com.soolr.api.entity.MessageSend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 消息发送接口
 * @author Soolr
 */
public interface MessageSendService extends ApiBaseService<MessageSend,String> {

    /**
     * 通过消息id删除
     * @param messageId
     */
    void deleteByMessageId(String messageId);

    /**
     * 多条件分页获取
     * @param messageSend
     * @param pageable
     * @return
     */
    Page<MessageSend> findByCondition(MessageSend messageSend, Pageable pageable);
}