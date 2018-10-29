package com.soolr.api.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Soolr
 */
@Data
public class OssSetting implements Serializable{

    @ApiModelProperty(value = "服务商")
    private String serviceName;

    @ApiModelProperty(value = "ak")
    private String accessKey;

    @ApiModelProperty(value = "sk")
    private String secretKey;

    @ApiModelProperty(value = "endpoint域名")
    private String endpoint;

    @ApiModelProperty(value = "bucket空间")
    private String bucket;

    @ApiModelProperty(value = "http")
    private String http;

    @ApiModelProperty(value = "zone存储区域")
    private Integer zone;

    @ApiModelProperty(value = "本地存储路径")
    private String filePath;

    @ApiModelProperty(value = "是否改变secrectKey")
    private Boolean changed;
}
