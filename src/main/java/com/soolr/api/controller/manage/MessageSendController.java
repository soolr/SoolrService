package com.soolr.api.controller.manage;

import com.soolr.api.base.ApiBaseController;
import com.soolr.api.base.ApiBaseService;
import com.soolr.api.common.utils.PageUtil;
import com.soolr.api.common.utils.ResultUtil;
import com.soolr.api.common.vo.PageVo;
import com.soolr.api.common.vo.Result;
import com.soolr.api.common.vo.SearchVo;
import com.soolr.api.entity.Message;
import com.soolr.api.entity.MessageSend;
import com.soolr.api.entity.User;
import com.soolr.api.service.MessageSendService;
import com.soolr.api.service.MessageService;
import com.soolr.api.service.UserService;
import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * @author Soolr
 */
@Slf4j
@RestController
@Api(description = "消息发送管理接口")
@RequestMapping("/api/messageSend")
@Transactional
public class MessageSendController extends ApiBaseController<MessageSend, String> {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageSendService messageSendService;

    @Override
    public MessageSendService getService() {
        return messageSendService;
    }

    @RequestMapping(value = "/getByCondition",method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    public Result<Page<MessageSend>> getByCondition(@ModelAttribute MessageSend ms,
                                                    @ModelAttribute PageVo pv){

        Page<MessageSend> page = messageSendService.findByCondition(ms, PageUtil.initPage(pv));
        // lambda
        page.getContent().forEach(item->{
            User u = userService.get(item.getUserId());
            item.setUsername(u.getUsername());
            Message m = messageService.get(item.getMessageId());
            item.setTitle(m.getTitle());
            item.setContent(m.getContent());
            item.setType(m.getType());
        });
        return new ResultUtil<Page<MessageSend>>().setData(page);
    }
}
