package com.gdkyit.core.web;

import com.gdkyit.core.domain.Result;
import com.gdkyit.core.domain.ResultCode;
import com.gdkyit.core.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class ExcelController {
    @Autowired
    private ExcelService excelService;

    @GetMapping("/excel")
    public void createExcel(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "15") Integer size, HttpServletResponse response) {
        excelService.createExcel(response, page, size);
    }

    @PostMapping("/excel")
    @ResponseBody
    public Result loadExcel(MultipartFile file) {
        return excelService.importExcel(file);
    }


    @GetMapping("/download")
    public String download() {
        return "example";
    }
}
