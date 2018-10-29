package com.soolr.api.exception;

import lombok.Data;

/**
 * @author Soolr
 */
@Data
public class ApiException extends RuntimeException {

    private String msg;

    public ApiException(String msg){
        super(msg);
        this.msg = msg;
    }
}
