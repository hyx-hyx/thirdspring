package com.example.thirdspring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping("/excel")
public class upload {
    @ResponseBody
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public void excleimport(@RequestParam  MultipartFile excelFile,
                                           HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String, Object> map = new HashMap<String, Object>();
        String name = excelFile.getOriginalFilename();
        if (!name.endsWith(".xls") && !name.endsWith(".xlsx")) {
            System.out.println("文件不是excel类型");
            map.put("filecondition", "文件类型错误");
        }
        else{
            AnalystExcel analystExcel=new AnalystExcel(excelFile.getInputStream(),name);
            analystExcel.BankListByExcel(excelFile.getInputStream(),name);
        }
    }
}