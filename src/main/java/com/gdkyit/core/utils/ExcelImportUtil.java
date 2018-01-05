package com.gdkyit.core.utils;

import com.gdkyit.core.dao.ExcelDao;
import com.gdkyit.core.domain.Result;
import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.WritableWorkbookImpl;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ExcelImportUtil {
    @Resource
    ExcelDao excelDao;

    //导入excel文件
    public static Result importExcel(MultipartFile files) {
        String filename = files.getOriginalFilename();
        List<List<String>> resultList = new ArrayList<>();
        if (filename.indexOf(".xls") > -1) {
            //正确的文件
            try {
                InputStream inputStream = files.getInputStream();
                //获取Excel文件对象
                Workbook workbook = Workbook.getWorkbook(inputStream);
                //获取文件的指定工作表 默认的第一个
                Sheet sheet = workbook.getSheet(0);
                //行数(表头的目录不需要，从1开始)
                for (int i = 0; i < sheet.getRows(); i++) {
                    //创建一个数组 用来存储每一列的值
                    List<String> result = new ArrayList<>();
                    Cell cell = null;
                    //列数
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        //获取第i行，第j列的值
                        cell = sheet.getCell(j, i);
                        result.add(cell.getContents());
                    }
                    resultList.add(result);
                }
                return Result.genSuccessResult(resultList);
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Result.genFailResult("文件格式不对,请导入正确的文件");
    }

    //导出excel文件
    public void createExcel(HttpServletResponse response, Integer page, Integer size) {
        //根据数据库生成不同的excel表格
        //根据参数判断是否有分页情况
        StringBuilder sql = new StringBuilder("select * from kxb_users ");
        if (page != null) {
            sql.append("limit " + page * size + "," + size);
        }
        List<Map<String, Object>> list = excelDao.getExcelDate(new String(sql));
        export(response, list);
    }

    public void export(HttpServletResponse response, List<Map<String, Object>> list) {
        //设置文件相关信息
        String fileName = "excel.xls";
        try {
            String downlaodFilename = URLEncoder.encode(fileName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //设置文件格式
        response.setContentType("application/vnd.ms-excel;charset=GBK");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        List<String> title = new ArrayList<>();
        if (list.size() == 0) return;
        try {
            WorkbookSettings workbookSettings = new WorkbookSettings();
            workbookSettings.setExcel9File(true);
            WritableWorkbook writableWorkbook = new WritableWorkbookImpl(response.getOutputStream(), true, workbookSettings);
            //工作空间
            CellView cellView = new CellView();
            cellView.setAutosize(true);
            WritableSheet writableSheet = writableWorkbook.createSheet("sheet1", 0);
            //writableSheet.setColumnView(1, cellView);
            Label label;
            //增加标题栏
            for (String key : list.get(0).keySet()) {
                title.add(key);
            }
            for (int i = 0; i < list.get(0).size(); i++) {
                label = new Label(i, 0, title.get(i));
                writableSheet.setColumnView(i + 1, cellView);//根据内容自动设置宽度
                writableSheet.addCell(label);
            }
            //增加数据
            int col;
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                col = -1;
                for (Object date :
                        map.values()) {
                    ++col;
                    if (date != null) {
                        if (date instanceof String) {
                            //字符串
                            label = new Label(col, i + 1, (String) date);
                            writableSheet.addCell(label);
                        } else if (date instanceof Date) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String newdate = sdf.format(date);
                            label = new Label(col, i + 1, newdate);
                            writableSheet.addCell(label);
                        } else {
                            String number = String.valueOf(date);
                            label = new Label(col, i + 1, number);
                            writableSheet.addCell(label);
                        }
                    }
                }
            }
            //写入数据
            writableWorkbook.write();
            writableWorkbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}