package com.gdkyit.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/1/25 0025.
 */
public class ParseMD5 {
    public static String parseStrToMd5L32(String str){
        String reStr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes){
                int bt = b&0xff;
                if (bt < 16){
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }
            reStr = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return reStr;
    }

    public static void main(String[] args) {
        String appKey = "5eb8877928b23a06";
        String query = "good";
        String salt = "2";
        String appSecret = "tnTzZdKlOPFUMGWDxCPxMaVOwmqKGYS9";
        String str1 = appKey + query + salt +appSecret;
        String name = parseStrToMd5L32(str1);
        System.out.println(name);
    }
}
