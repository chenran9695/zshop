package com.cr.zshop.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringUtils {
    /**
     * 工具类方法：修改文件名
     * 命名规则：日期（yyyyMMddHHmmss）+随机数（100以内）+原文件名；
     * */
    public static String renameFileName(String fileName){
        int dotIndex = fileName.lastIndexOf(".");
        String suffix = fileName.substring(dotIndex);
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+new Random().nextInt(100)+suffix;
    }
}
