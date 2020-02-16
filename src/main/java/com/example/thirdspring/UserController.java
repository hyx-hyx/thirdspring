package com.example.thirdspring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.thirdspring.importSql.random;

@Controller
public class UserController {
    private JdbcTemplate jdbcTemplate;
    private ResultSet rs;
  @RequestMapping("/index")
    public String index(Model model) {
      User showuser=new User();
      showuser=random();
      //List<String> luck=new ArrayList<String>();
      //luck.add(showuser.getMark());
      //luck.add(showuser.getName());
        model.addAttribute("mark",showuser.getMark());
        model.addAttribute("name",showuser.getName());
        return "index.html";
    }

}
