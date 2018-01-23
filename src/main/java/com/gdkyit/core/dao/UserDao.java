package com.gdkyit.core.dao;

import com.gdkyit.core.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/5 0005.
 */
@Repository
public class UserDao extends BaseDao {
    final static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    public List<Map<String,Object>> getAll(Map<String,Object> params){
        StringBuffer stringBuffer = new StringBuffer("select * from kxb_users where 1=1 ");
        List<Object> args = new ArrayList<Object>();

        //根据查询条件，变化sql
        String names = (String)params.get("names");
        String phone = (String)params.get("phone");
        String password = (String)params.get("password");
        int PageSize = (int)params.get("totalPages")/10 ;
        int PageNo = (int)params.get("currentPage");
        if(params.size() != 0 ){
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
        }

        //分页查询
        stringBuffer.append(" limit ?,? ");
        args.add(PageSize * (PageNo - 1));
        args.add(PageSize);


        List<Map<String,Object>> list = jdbcTemplate.queryForList(stringBuffer.toString(),args.toArray());
        /**
         * 对 list 时间格式化
         */
        System.out.println(list.get(0).get("CREATE_TIME"));
        return list;

}


    public void deleteById(Integer id){
        jdbcTemplate.update("DELETE FROM kxb_users WHERE ID=?",new Object[]{id});
    }

    public void alert(Map<String,Object> params){
        StringBuffer stringBuffer = new StringBuffer("UPDATE kxb_users ");
        stringBuffer.append("SET VERSION=?,USERNAME=?,PASSWORD=?,ACCOUNT_ENABLED=?,ACCOUNT_EXPIRED=?,ACCOUNT_LOCKED=?,CREDENTIALS_EXPIRED=?,NAMES=?,PHONE=? ");
        stringBuffer.append("WHERE ID=?");
        jdbcTemplate.update(stringBuffer.toString(),new Object[]{params.get("version"),params.get("username"),params.get("password"),params.get("accountEnable"),params.get("accountExpired"),params.get("accountLocked"),params.get("credentialsExpired"),params.get("names"),params.get("phone"),params.get("id")});
    }

    public void add(Map<String,Object> params){
        String sql = "INSERT INTO kxb_users VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{params.get("id"),params.get("version"),params.get("username"),params.get("password"),params.get("accountEnable"),params.get("accountExpired"),params.get("accountLocked"),params.get("credentialsExpired"),params.get("names"),params.get("phone"),df.format(new Date())});
    }


    public List<Map<String,Object>> getUserInfoById(Integer id) {
        String sql = "select * from kxb_users where ID=?";
        return jdbcTemplate.queryForList(sql,new Object[]{id});
    }

    /**
     * 当查询结果是一个对象时， 这种方法最好用。
     * 没有完整查询出一个User,只为应付Tokens所需
     * @return
     */
    public User findOneByUsername(String username) {
//        final User user = new User();
//        jdbcTemplate.query("select * from kxb_users where username=?", new Object[]{username}, new RowCallbackHandler() {
//            @Override
//            public void processRow(ResultSet rs) throws SQLException {
//                user.setId(rs.getLong("ID"));
//                user.setUsername(rs.getString("USERNAME"));
//                user.setPassword(rs.getString("PASSWORD"));
//            }
//        });
        /**
         * 使用jdbcTemplate.queryForObject
         */
        String sql = "select * from kxb_users where username=?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User user = jdbcTemplate.queryForObject(sql,rowMapper,new Object[]{username});
        return user;
    }



}