package com.gdkyit.core.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ExcelDao extends BaseDao {
    //获取数据
    public List<Map<String, Object>> getExcelDate(String sql) {
        return this.jdbcTemplate.queryForList(sql);
    }
}
