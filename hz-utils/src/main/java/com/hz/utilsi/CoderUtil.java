package com.hz.utilsi;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class CoderUtil {


    public static String urlEncode(String s){
        try {
         return    java.net.URLEncoder.encode(s,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String urlDecode(String s){
        try {
            return    URLDecoder.decode(s,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

}
