package com.gdkyit.core.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BaseInfoDao extends BaseDao {

    public List<Map<String,Object>> getDblist() {
        String sql = "select * from seed_users";
        return this.jdbcTemplate.queryForList(sql);
    }
}
