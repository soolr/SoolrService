package com.soolr.api.controller.common;

import com.soolr.api.common.constant.CommonConstant;
import com.soolr.api.common.constant.SettingConstant;
import com.soolr.api.common.utils.CreateVerifyCode;
import com.soolr.api.common.utils.ResultUtil;
import com.soolr.api.common.utils.SmsUtil;
import com.soolr.api.common.vo.Captcha;
import com.soolr.api.common.vo.Result;
import com.soolr.api.common.vo.SmsSetting;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Soolr
 */
@Api(description = "验证码接口")
@RequestMapping("/api/common/captcha")
@RestController
@Transactional
@Slf4j
public class CaptchaController {

    @Autowired
    private SmsUtil smsUtil;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/init",method = RequestMethod.GET)
    @ApiOperation(value = "初始化验证码")
    public Result<Object> initCaptcha() {

        String captchaId = UUID.randomUUID().toString().replace("-","");
        String code = new CreateVerifyCode().randomStr(4);
        Captcha captcha = new Captcha();
        captcha.setCaptchaId(captchaId);
        //缓存验证码
        redisTemplate.opsForValue().set(captchaId,code,3L, TimeUnit.MINUTES);
        return new ResultUtil<Object>().setData(captcha);
    }

    @RequestMapping(value = "/draw/{captchaId}", method = RequestMethod.GET)
    @ApiOperation(value = "根据验证码ID获取图片")
    public void drawCaptcha(@PathVariable("captchaId") String captchaId, HttpServletResponse response) throws IOException {

        // 得到验证码 生成指定验证码
        String code=redisTemplate.opsForValue().get(captchaId);
        CreateVerifyCode vCode = new CreateVerifyCode(116,36,4,10,code);
        vCode.write(response.getOutputStream());
    }

    @RequestMapping(value = "/sendSms/{mobile}",method = RequestMethod.GET)
    @ApiOperation(value = "发送短信验证码")
    public Result<Object> sendSms(@PathVariable String mobile) {

        // 生成6位数验证码
        String code = new CreateVerifyCode().getRandomNum();
        //缓存验证码
        redisTemplate.opsForValue().set(CommonConstant.PRE_SMS + mobile, code,5L, TimeUnit.MINUTES);
        // 发送验证码
        try {
            // 获取模板
            String templateCode = redisTemplate.opsForValue().get(SettingConstant.ALI_SMS_COMMON);
            SendSmsResponse response = smsUtil.sendSms(mobile, code, templateCode);
            if(response.getCode() != null && ("OK").equals(response.getMessage())) {
                //请求成功
                return new ResultUtil<Object>().setData("发送短信验证码成功");
            }else{
                return new ResultUtil<Object>().setErrorMsg("请求发送验证码失败，" + response.getMessage());
            }
        } catch (ClientException e) {
            log.error("请求发送验证码失败，" + e);
            return new ResultUtil<Object>().setErrorMsg("请求发送验证码失败");
        }
    }
}
