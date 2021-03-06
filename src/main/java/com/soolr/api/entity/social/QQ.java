package com.soolr.api.entity.social;

import com.soolr.api.base.ApiBaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Soolr
 */
@Data
@Entity
@Table(name = "t_qq")
@TableName("t_qq")
public class QQ extends ApiBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "qq唯一id")
    private String openId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "是否绑定账号 默认false")
    private Boolean isRelated = false;

    @ApiModelProperty(value = "绑定用户账号")
    private String relateUsername;
}