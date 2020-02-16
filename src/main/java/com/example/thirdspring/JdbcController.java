package com.example.thirdspring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author oyc
 * @Description: 用户控制类
 * @date 2018/7/8 22:10
 */

@Controller
public class JdbcController {
    private JdbcTemplate jdbcTemplate;
    private ResultSet rs;
    public String random(){
            String randomstate = "select * from excelstudent AS t1" + "\n"
                    + "JOIN(" + "\n"
                    + "SELECT ROUND(RAND()*((SELECT MAX(student_id) FROM excelstudent)-(SELECT MIN(student_id) FROM excelstudent))+(SELECT MIN(student_id) FROM excelstudent)) AS student_id" + "\n"
                    + ") AS t2" + "\n"
                    + "WHERE t1.student_id>=t2.student_id" + "\n"
                    + "ORDER BY t1.student_id LIMIT 1" + "\n";
            Map user = jdbcTemplate.queryForMap(randomstate);
            String username = (String) user.get("姓名");
            System.out.println(username);
            return username;
    }
}