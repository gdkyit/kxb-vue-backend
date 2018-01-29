package com.gdkyit.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Administrator on 2018/1/24 0024.
 */
public class TimeConverterUtil {

    /**
     * 本业务所用
     * utc时间转本地时间
     * @param utcTime
     * @return
     */
    public static String utcTolocal(String utcTime) {
        return utc2Local(utcTime, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 函数功能描述:UTC时间转本地时间格式
     *
     * @param utcTime         UTC时间
     * @param utcTimePatten   UTC时间格式
     * @param localTimePatten 本地时间格式
     * @return 本地时间格式的时间
     * eg:utc2Local("2017-06-14 09:37:50.788+08:00", "yyyy-MM-dd HH:mm:ss.SSSXXX", "yyyy-MM-dd HH:mm:ss.SSS")
     */
    public static String utc2Local(String utcTime, String utcTimePatten, String localTimePatten) {
        if (utcTime == null || utcTime.equals("")) {
            return null;
        }
        SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePatten);
        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));//时区定义并进行时间获取
        Date gpsUTCDate = null;
        try {
            gpsUTCDate = utcFormater.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return utcTime;
        }
        SimpleDateFormat localFormater = new SimpleDateFormat(localTimePatten);
        localFormater.setTimeZone(TimeZone.getDefault());
        String localTime = localFormater.format(gpsUTCDate.getTime());
        return localTime;
    }

}
