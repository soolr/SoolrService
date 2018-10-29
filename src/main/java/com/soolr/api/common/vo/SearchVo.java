package com.soolr.api.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Soolr
 */
@Data
public class SearchVo implements Serializable {

    private String startDate;

    private String endDate;
}
