package com.gdkyit.core.dao.impl;

import com.gdkyit.core.dao.BaseDao;
import com.gdkyit.core.dao.UserDao;
import com.gdkyit.core.entity.User;
import com.gdkyit.core.utils.TimeConverterUtil;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/** 2018年3月30日
 * Created by Administrator on 2017/12/5 0005.
 */
@Repository
public class UserDaoImpl extends BaseDao implements UserDao {
    final static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    public List<Map<String, Object>> getAll(Map<String, Object> params) {
        StringBuffer stringBuffer = new StringBuffer("select * from kxb_users where 1=1 ");
        List<Object> args = new ArrayList<Object>();

        //根据查询条件，变化sql
        String names = (String) params.get("names");
        String phone = (String) params.get("phone");
        String password = (String) params.get("password");

        String timeSUTC = (String) params.get("timeS");
        String timeEUTC = (String) params.get("timeE");
        String timeS = TimeConverterUtil.utcTolocal(timeSUTC);
        String timeE = TimeConverterUtil.utcTolocal(timeEUTC);

        if (params.size() != 0) {
            if (names != null && !"".equals(names)) {
                stringBuffer.append("and NAMES like ? ");
                args.add("%" + names + "%");
            }

            if (phone != null && !"".equals(phone)) {
                stringBuffer.append(" and PHONE like ? ");
                args.add("%" + phone + "%");
            }

            if (password != null && !"".equals(password)) {
                stringBuffer.append(" and PASSWORD like ? ");
                args.add("%" + password + "%");
            }

            if (timeS != null) {
                stringBuffer.append("and CREATE_TIME>=? ");
                args.add(timeS);
            }

            if (timeE != null) {
                stringBuffer.append("and CREATE_TIME<=? ");
                args.add(timeE);
            }
        }

        //总条目数
        final Integer[] totalCount = {null};
        jdbcTemplate.query("SELECT COUNT(*) FROM `kxb_users` ", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                totalCount[0] = rs.getInt("COUNT(*)");
            }
        });

        //每页显示条目个数
        int PageSize = (int) params.get("pageSize");

        //当前页
        int PageNo = (int) params.get("currentPage");


        //分页查询
        stringBuffer.append(" limit ?,? ");
        args.add(PageSize * (PageNo - 1));
        args.add(PageSize);

        /**
         * 为了对时间格式化竟然要自己封装,还能好好玩耍么？
         */
        List<Map<String, Object>> list = jdbcTemplate.query(stringBuffer.toString(), args.toArray(), new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
                Map<String, Object> map = new HashMap<>();
                map.put("ID", rs.getInt("ID"));
                map.put("VERSION", rs.getString("VERSION"));
                map.put("USERNAME", rs.getString("USERNAME"));
                map.put("PASSWORD", rs.getString("PASSWORD"));
                map.put("ACCOUNT_ENABLED", rs.getInt("ACCOUNT_ENABLED"));
                map.put("ACCOUNT_EXPIRED", rs.getInt("ACCOUNT_EXPIRED"));
                map.put("ACCOUNT_LOCKED", rs.getInt("ACCOUNT_LOCKED"));
                map.put("CREDENTIALS_EXPIRED", rs.getInt("CREDENTIALS_EXPIRED"));
                map.put("NAMES", rs.getString("NAMES"));
                map.put("PHONE", rs.getString("PHONE"));
                map.put("CREATE_TIME", df.format(rs.getTimestamp("CREATE_TIME")));

                //总条目数
                map.put("total", totalCount[0]);
                return map;
            }
        });
        return list;

    }


    public void deleteById(Integer id) {
        jdbcTemplate.update("DELETE FROM kxb_users WHERE ID=?", new Object[]{id});
    }

    public void alert(Map<String, Object> params) {
        StringBuffer stringBuffer = new StringBuffer("UPDATE kxb_users ");
        stringBuffer.append("SET VERSION=?,USERNAME=?,PASSWORD=?,ACCOUNT_ENABLED=?,ACCOUNT_EXPIRED=?,ACCOUNT_LOCKED=?,CREDENTIALS_EXPIRED=?,NAMES=?,PHONE=? ");
        stringBuffer.append("WHERE ID=?");
        jdbcTemplate.update(stringBuffer.toString(), new Object[]{params.get("version"), params.get("username"), params.get("password"), params.get("accountEnable"), params.get("accountExpired"), params.get("accountLocked"), params.get("credentialsExpired"), params.get("names"), params.get("phone"), params.get("id")});
    }

    public void add(Map<String, Object> params) {
        String sql = "INSERT INTO kxb_users VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{params.get("id"), params.get("version"), params.get("username"), params.get("password"), params.get("accountEnable"), params.get("accountExpired"), params.get("accountLocked"), params.get("credentialsExpired"), params.get("names"), params.get("phone"), df.format(new Date())});
    }


    public List<Map<String, Object>> getUserInfoById(Integer id) {
        String sql = "select * from kxb_users where ID=?";
        return jdbcTemplate.queryForList(sql, new Object[]{id});
    }

    /**
     * 当查询结果是一个对象时， 这种方法最好用。
     *
     * @return
     */
    public User findOneByUsername(String username) {
        /**
         * 使用jdbcTemplate.queryForObject
         */
        String sql = "select * from kxb_users where username=?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User user = jdbcTemplate.queryForObject(sql, rowMapper, new Object[]{username});
        return user;
    }

    @Override
    public void batchDelete(Map<String, Object> params) {
        String ids = (String)params.get("ids");
        final String[] idsArray = ids.split(",");
//        final List<String> idsList = Arrays.asList(idsArray);
        /*for(String i: idsArray) {
            jdbcTemplate.update("DELETE FROM kxb_users WHERE ID=?", new Object[]{i});
        }*/


        jdbcTemplate.batchUpdate("DELETE FROM kxb_users WHERE ID=?", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,idsArray[i]);
            }

            //这里指定批量删除的数量大小，坑
            @Override
            public int getBatchSize() {
                return idsArray.length;
            }
        });
    }


}