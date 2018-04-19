package com.gdkyit.core.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/25 0025.
 */
@Repository
public class ExcelDao extends BaseDao{

    public List<Map<String, Object>> getExcelDate(String sql){
        return jdbcTemplate.queryForList(sql);
    }
}
