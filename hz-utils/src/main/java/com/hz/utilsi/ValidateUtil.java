package com.hz.utilsi;

import java.util.ResourceBundle;

/**
 * @Author:wangtao176
 * @Description:
 * @Date:Created in 23:52 2018/3/1
 **/
public class ValidateUtil {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("message");

    public static String getMessage(String key) {
        return bundle.getString(key);
    }
}
