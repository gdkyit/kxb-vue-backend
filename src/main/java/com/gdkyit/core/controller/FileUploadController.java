package com.gdkyit.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * 文件上传接口
 * Created by Administrator on 2017/12/13 0013.
 */
@Controller
public class FileUploadController {
    /**
     * 单文件上传测试页面
     */
    @GetMapping("fileupload")
    public String fileUploadIndex(){
        return "/fileupload";
    }

    /**
     * 多文件上传测试页面
     */
    @GetMapping("mulfileupload")
    public String mulfileUploadIndex(){
        return "/mulfileupload";
    }

    /**
     * 单文件上传
     * @param file
     * @return
     */
    @PostMapping("/file/upload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile file){
        if(!file.isEmpty()){
            try{
                //文件直接输出到项目路径下
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
                return "上传成功";
            }catch (FileNotFoundException e){
                return "上传失败，" +  e.getMessage();
            } catch (IOException e) {
                return "上传失败，" +  e.getMessage();
            }
        }else {
            return "上传失败，因为文件是空的.";
        }
    }

    /**
     * 多文件上传
     */
    @RequestMapping(value = "/mulfile/upload", method = RequestMethod.POST)
    public @ResponseBody String batchUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "上传失败 " + i + " => " + e.getMessage();
                }
            } else {
                return "上传失败 " + i + " 文件为空";
            }
        }
        return "上传成功";
    }
}
