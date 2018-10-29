package com.soolr.api.controller.scocial;

import com.soolr.api.common.constant.CommonConstant;
import com.soolr.api.common.constant.SettingConstant;
import com.soolr.api.common.utils.IpInfoUtil;
import com.soolr.api.common.utils.ResultUtil;
import com.soolr.api.common.utils.SecurityUtil;
import com.soolr.api.common.vo.RelateUserInfo;
import com.soolr.api.common.vo.Result;
import com.soolr.api.common.vo.VaptchaSetting;
import com.soolr.api.dao.UserDao;
import com.soolr.api.entity.User;
import com.soolr.api.entity.social.Github;
import com.soolr.api.entity.social.QQ;
import com.soolr.api.entity.social.Weibo;
import com.soolr.api.exception.ApiException;
import com.soolr.api.service.GithubService;
import com.soolr.api.service.QQService;
import com.soolr.api.service.UserService;
import com.soolr.api.service.WeiboService;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Struct;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * @author Soolr
 */
@Slf4j
@Api(description = "绑定第三方账号接口")
@RequestMapping("/api/social")
@RestController
public class RelateController {

    @Autowired
    private UserService userService;

    @Autowired
    private GithubService githubService;

    @Autowired
    private QQService qqService;

    @Autowired
    private WeiboService weiboService;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private IpInfoUtil ipInfoUtil;

    private static final String VAPTCHA_URL = "http://api.vaptcha.com/v2/validate";

    public VaptchaSetting getVaptchaSetting(){

        String v = redisTemplate.opsForValue().get(SettingConstant.VAPTCHA_SETTING);
        if(StrUtil.isBlank(v)){
            throw new ApiException("您还未配置Vaptcha验证码");
        }
        return new Gson().fromJson(v, VaptchaSetting.class);
    }

    @RequestMapping(value = "/relate",method = RequestMethod.POST)
    @ApiOperation(value = "绑定账号")
    public Result<Object> relate(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam Integer socialType,
                                 @RequestParam String id,
                                 @RequestParam String token,
                                 HttpServletRequest request){

        VaptchaSetting vs = getVaptchaSetting();
        // 验证验证码
        String params = "id=" + vs.getVid() + "&secretkey=" + vs.getSecretKey() + "&token=" + token
                + "&ip=" + ipInfoUtil.getIpAddr(request);
        String result = HttpUtil.post(VAPTCHA_URL, params);
        log.info(result);
        if(!result.contains("\"success\":1")){
            return new ResultUtil<Object>().setErrorMsg("验证码验证失败");
        }
        User user = userService.findByUsername(username);
        if(user==null){
            return new ResultUtil<Object>().setErrorMsg("账号不存在");
        }
        if(!new BCryptPasswordEncoder().matches(password, user.getPassword())){
            return new ResultUtil<Object>().setErrorMsg("密码不正确");
        }
        String JWT = "";
        // 从redis中获取表id
        String ID = redisTemplate.opsForValue().get(id);
        if(StrUtil.isBlank(ID)){
            return new ResultUtil<Object>().setErrorMsg("无效的id");
        }
        // 绑定github
        if(CommonConstant.SOCIAL_TYPE_GITHUB.equals(socialType)){
            Github github = githubService.get(ID);
            if(github==null){
                return new ResultUtil<Object>().setErrorMsg("绑定失败，请先进行第三方授权认证");
            }
            if(github.getIsRelated()&&StrUtil.isNotBlank(github.getRelateUsername())){
                return new ResultUtil<Object>().setErrorMsg("该账号已绑定Github账号，请先进行解绑操作");
            }
            github.setIsRelated(true);
            github.setRelateUsername(username);
            githubService.update(github);
            JWT = securityUtil.getToken(username, true);
        }else if(CommonConstant.SOCIAL_TYPE_QQ.equals(socialType)){
            QQ qq = qqService.get(ID);
            if(qq==null){
                return new ResultUtil<Object>().setErrorMsg("绑定失败，请先进行第三方授权认证");
            }
            if(qq.getIsRelated()&&StrUtil.isNotBlank(qq.getRelateUsername())){
                return new ResultUtil<Object>().setErrorMsg("该账号已绑定QQ账号，请先进行解绑操作");
            }
            qq.setIsRelated(true);
            qq.setRelateUsername(username);
            qqService.update(qq);
            JWT = securityUtil.getToken(username, true);
        }else if(CommonConstant.SOCIAL_TYPE_WEIBO.equals(socialType)){
            Weibo weibo = weiboService.get(ID);
            if(weibo==null){
                return new ResultUtil<Object>().setErrorMsg("绑定失败，请先进行第三方授权认证");
            }
            if(weibo.getIsRelated()&&StrUtil.isNotBlank(weibo.getRelateUsername())){
                return new ResultUtil<Object>().setErrorMsg("该账号已绑定微博账号，请先进行解绑操作");
            }
            weibo.setIsRelated(true);
            weibo.setRelateUsername(username);
            weiboService.update(weibo);
            JWT = securityUtil.getToken(username, true);
        }
        // 存入redis
        String JWTKey = UUID.randomUUID().toString().replace("-","");
        redisTemplate.opsForValue().set(JWTKey, JWT, 2L, TimeUnit.MINUTES);
        // 绑定成功删除缓存
        redisTemplate.delete("relate::relatedInfo:" + username);
        return new ResultUtil<Object>().setData(JWTKey);
    }

    @RequestMapping(value = "/getJWT",method = RequestMethod.GET)
    @ApiOperation(value = "获取JWT")
    public Result<Object> getJWT(@RequestParam String JWTKey){

        String JWT = redisTemplate.opsForValue().get(JWTKey);
        if(StrUtil.isBlank(JWT)){
            return new ResultUtil<Object>().setErrorMsg("获取JWT失败");
        }
        return new ResultUtil<Object>().setData(JWT);
    }
}
