package com.soolr.api.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Soolr
 */
@Data
public class IpLocate implements Serializable {

    private String retCode;

    private City result;
}

