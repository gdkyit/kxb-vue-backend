package com.gdkyit.core.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {

    @RequestMapping("/example")
    public String getIndex() {
        // 加入一个属性，用来在模板中读取
        // return模板文件的名称，对应src/main/resources/templates/example.html
        return "example";
    }
}
