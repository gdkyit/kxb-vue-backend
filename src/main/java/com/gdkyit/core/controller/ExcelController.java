package com.gdkyit.core.controller;

import com.gdkyit.core.domain.Result;
import com.gdkyit.core.utils.ExcelImportUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/12/15 0015.
 */
@Controller
public class ExcelController {
    @Resource
    ExcelImportUtil excelImportUtil;

    /**
     *  解析excel
     */
    @PostMapping("/excel/import")
    @ResponseBody
    public Result importExcel(MultipartFile file){
        return excelImportUtil.importExcel(file);
    }


    /**
     *  导出excel
     */
    @GetMapping("/excel/export")
    public Result exportExcel(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "15") Integer size, HttpServletResponse response){
        excelImportUtil.createExcel(response, page, size);
        return Result.genSuccessResult();
    }

}
