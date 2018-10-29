package com.soolr.api.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Soolr
 */
@Data
public class CheckValidate implements Serializable {

    private String userId;

    private String email;

    private String validateUrl;
}
