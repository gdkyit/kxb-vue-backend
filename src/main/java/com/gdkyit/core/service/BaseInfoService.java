package com.gdkyit.core.service;

import com.gdkyit.core.dao.BaseDao;
import com.gdkyit.core.dao.BaseInfoDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class BaseInfoService {

    @Resource
    private BaseInfoDao basedao;

    public List<Map<String, Object>> getDblist() {
        return basedao.getDblist();
    }

}
