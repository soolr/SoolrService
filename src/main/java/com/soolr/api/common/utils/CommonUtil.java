package com.soolr.api.common.utils;

import java.util.UUID;

/**
 * @author Soolr
 */
public class CommonUtil {

    /**
     * 以UUID重命名
     *
     * @param fileName
     * @return
     */
    public static String renamePic(String fileName) {
        String extName = fileName.substring(fileName.lastIndexOf("."));
        return UUID.randomUUID().toString().replace("-", "") + extName;
    }
}
