package com.gdkyit.core.utils;

import org.json.JSONObject;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 通过socre来获取openid
 * Created by Administrator on 2018/2/7 0007.
 */
public class GetOpenid {
    private static final String APPID = "wx7e6e712a354d42e7";
    private static final String SECRET = "da1439cf817360fc0a63c576da9c1173";


    public static String getOpenidByScore(String score) {
        /**
         * 拼接URL
         * 官方api: https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
         */
        StringBuffer url = new StringBuffer("https://api.weixin.qq.com/sns/jscode2session?appid=");
        url.append(APPID);
        url.append("&secret=");
        url.append(SECRET);
        url.append("&js_code=");
        url.append(score);
        url.append("&grant_type=authorization_code");

        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(1000);
        simpleClientHttpRequestFactory.setReadTimeout(1000);
        RestTemplate restTemplate = new RestTemplate(simpleClientHttpRequestFactory);

        //把结果直接装在一个字符串
        String result = restTemplate.getForObject(url.toString(),String.class);

        JSONObject jsonObj = new JSONObject(result);
        String openid = jsonObj.optString("openid");
        return openid;
    }

    public static void main(String[] args) {
        System.out.println("openid: "+ getOpenidByScore("003WK0C21KU9VN1iqQD21qv4C21WK0Cd"));
    }

}


