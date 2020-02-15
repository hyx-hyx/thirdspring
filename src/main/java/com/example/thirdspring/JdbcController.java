/*package com.example.thirdspring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;*/
/**
 * @author oyc
 * @Description: 用户控制类
 * @date 2018/7/8 22:10
 */
/*
@Controller
@RequestMapping("/jdbc")
public class JdbcController {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/result")
    public User random(ModelMap map){
        String sql1="SELECT * FROM user "
        String sql2="WHERE id >=(SELECT MAX(id) FROM city)-(SELECT MIN(id) FROM city))*RAND() + (SELECT MIN(id) FROM "+user+") LIMIT 1";
        List<User> userList=jdbcTemplate.query(sql1,new RowMapper<User>)(){
            User user=null;

            public User mapRow(ResultSet rs,int rowNum)throws SQLException{
                user=new User();
                user.setId(user.getString("id"));
                user.setName(user.getString("name"));
                user.setMark(user.getString("mark"));
                return user;
            }
        });
        for(User user:userList){
            System.out.println(user.getName());
        }
        map.addAttribute("users",userList);
        return "user";
    }

}*/