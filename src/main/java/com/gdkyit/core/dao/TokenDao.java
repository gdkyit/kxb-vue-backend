package com.gdkyit.core.dao;

import com.gdkyit.core.entity.Token;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/19 0019.
 */
@Repository
public class TokenDao extends BaseDao {
    //设置日期格式
    final static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public Token findOneByUsername(String username) {
        final Token token = new Token();
        jdbcTemplate.query("select * from kxb_token where username=?", new Object[]{username}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                token.setId(rs.getLong("id"));
                token.setUsername(rs.getString("username"));
                token.setTokenname(rs.getString("tokenname"));
                token.setCreatetime(rs.getTimestamp("createtime"));
            }
        });
        return token;
    }

    public void saveToken(String username, String newToken) {
        String sql = "INSERT INTO kxb_token(username,tokenname,createtime) VALUES(?,?,?)";
        jdbcTemplate.update(sql,username,newToken,df.format(new Date()));
    }

    public List<Map<String,Object>> getAll() {
        List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from kxb_token");
        return list;
    }
}
