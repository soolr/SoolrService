package com.soolr.api.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Soolr
 */
@Data
public class City implements Serializable {

    String country;

    String province;

    String city;
}
