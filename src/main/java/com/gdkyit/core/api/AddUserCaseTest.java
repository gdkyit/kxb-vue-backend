package com.gdkyit.core.api;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2018/1/24 0024.
 */
@Controller
public class AddUserCaseTest {
    @Resource
    JdbcTemplate jdbcTemplate;

    /**
     * 批量增加测试数据
     */
    @RequestMapping(value = "/api/addMany")
    @ResponseBody
    public String insertMany() {
        SimpleDateFormat df = new SimpleDateFormat(" yy-MM-dd HH:mm:ss");
        for (int i = 1; i <= 20; i++) {
            String sql = "INSERT INTO " +
                    "kxb_users(`VERSION`,`USERNAME`,`PASSWORD`,`ACCOUNT_ENABLED`,`ACCOUNT_EXPIRED`,`ACCOUNT_LOCKED`,`CREDENTIALS_EXPIRED`,`NAMES`,`PHONE`,`CREATE_TIME`) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)";
            String username = getRandomString(3);
            String names =  username.toUpperCase();

            String phone = (new StringBuffer("1").append(getRadomNumber(10))).toString();
            jdbcTemplate.update(sql,new Object[]{1.0,username,123456,1,1,1,1,names,phone,df.format(new Date())});
        }
        return "已经在数据库新增加20条记录";
    }


    /**
     * Java生成随机字符串的代码
     */
    private static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    /**
     * 需要的长度
     * @param length
     */
    private static String getRadomNumber(int length){
        //获取一个随机数
        double rand = Math.random();
        //将随机数转换为字符串
        String str = String.valueOf(rand).replace("0.", "");
        //截取字符串
        String newStr = str.substring(0, length);
        return newStr;
    }
}
