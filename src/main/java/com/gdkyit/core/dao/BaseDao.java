package com.gdkyit.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao {

    @Autowired
    public
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate npJdbcTemplate;


}
