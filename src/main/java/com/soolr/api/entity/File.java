package com.soolr.api.entity;

import com.soolr.api.base.ApiBaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Soolr
 */
@Data
@Entity
@Table(name = "t_file")
@TableName("t_file")
public class File extends ApiBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "原文件名")
    private String name;

    @ApiModelProperty(value = "存储文件名")
    private String fKey;

    @ApiModelProperty(value = "大小")
    private Long size;

    @ApiModelProperty(value = "文件类型")
    private String type;

    @ApiModelProperty(value = "路径")
    private String url;

    @ApiModelProperty(value = "存储位置 0本地 1七牛 2阿里")
    private Integer location;
}