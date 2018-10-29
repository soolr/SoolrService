package com.soolr.api.controller.manage;


import com.soolr.api.common.constant.SettingConstant;
import com.soolr.api.common.limit.RedisRaterLimiter;
import com.soolr.api.common.utils.EmailUtil;
import com.soolr.api.common.utils.IpInfoUtil;
import com.soolr.api.common.utils.ResultUtil;
import com.soolr.api.common.vo.CheckValidate;
import com.soolr.api.common.vo.OtherSetting;
import com.soolr.api.common.vo.Result;
import com.soolr.api.entity.User;
import com.soolr.api.exception.ApiException;
import com.soolr.api.service.UserService;
import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Soolr
 */
@Slf4j
@Controller
@Api(description = "邮箱验证接口")
@RequestMapping("/api/email")
@Transactional
public class ValidateEmailController {

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private IpInfoUtil ipInfoUtil;

    @Autowired
    private RedisRaterLimiter redisRaterLimiter;

    public OtherSetting getOtherSetting(){

        String v = redisTemplate.opsForValue().get(SettingConstant.OTHER_SETTING);
        if(StrUtil.isBlank(v)){
            throw new ApiException("您还未配置服务器域名");
        }
        return new Gson().fromJson(v, OtherSetting.class);
    }

    @RequestMapping(value = "/sendCheckEmail",method = RequestMethod.POST)
    @ApiOperation(value = "发送验证邮箱")
    @ResponseBody
    public Result<Object> sendCheckEmail(@RequestParam String id,
                                         @RequestParam String email,
                                         HttpServletRequest request){

        // IP限流 2分钟限1个请求
        String tokenLimit = redisRaterLimiter.acquireTokenFromBucket("sendCheckEmail:"+ipInfoUtil.getIpAddr(request), 1, 120000);
        if (StrUtil.isBlank(tokenLimit)) {
            throw new ApiException("您发送的太频繁啦，请2分钟后再试");
        }

        String token = UUID.randomUUID().toString().replace("-", "");
        // 生成token 存入表id和email以及url
        CheckValidate c = new CheckValidate();
        c.setUserId(id);
        c.setEmail(email);
        String validateUrl = getOtherSetting().getDomain() + "/api/email/validate/" + token;
        c.setValidateUrl(validateUrl);
        redisTemplate.opsForValue().set(token, new Gson().toJson(c, CheckValidate.class), 30L, TimeUnit.MINUTES);

        emailUtil.sendTemplateEmail(email, "【XBoot】邮箱修改验证","check-email", c);
        return new ResultUtil<Object>().setData(null);
    }

    @RequestMapping(value = "/validate/{token}",method = RequestMethod.GET)
    @ApiOperation(value = "发送验证邮箱")
    public String emailValidate(@PathVariable String token) throws UnsupportedEncodingException {

        String v = redisTemplate.opsForValue().get(token);
        if(StrUtil.isBlank(v)){
            return "redirect:" + getOtherSetting().getDomain() + "/email-result?result=0";
        }
        CheckValidate c = new Gson().fromJson(v, CheckValidate.class);
        User u = userService.get(c.getUserId());
        u.setEmail(c.getEmail());
        userService.update(u);
        // token失效
        redisTemplate.delete(token);
        // 删除缓存
        redisTemplate.delete("user::"+u.getUsername());
        return "redirect:" + getOtherSetting().getDomain() + "/email-result?result=1";
    }
}
