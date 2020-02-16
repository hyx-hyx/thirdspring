package com.example.thirdspring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author oyc
 * @Description:用户实体类
 * @date 2018/7/8 22:51
 */
public class User{
    private String name;
    private String mark;
    User()
    {
        this.name=null;
        this.mark=null;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setMark(String mark){
        this.mark=mark;
    }
    public String getMark(){
        return mark;
    }
    public static User random() {
        connectSql sql = new connectSql();
        Connection conn = sql.getconnection();
        User user = new User();
        try {
            Statement sm = conn.createStatement();
            //向已有的表中插入学号，id会自动增加
            String randomstate = "select * from excelstudent AS t1" + "\n"
                    + "JOIN(" + "\n"
                    + "SELECT ROUND(RAND()*((SELECT MAX(student_id) FROM excelstudent)-(SELECT MIN(student_id) FROM excelstudent))+(SELECT MIN(student_id) FROM excelstudent)) AS student_id" + "\n"
                    + ") AS t2" + "\n"
                    + "WHERE t1.student_id>=t2.student_id" + "\n"
                    + "ORDER BY t1.student_id LIMIT 1" + "\n";
            ResultSet rs = sm.executeQuery(randomstate);
            while (rs.next()) {
                user.setMark(rs.getString("学号"));
                user.setName(rs.getString("姓名"));
                System.out.println(rs.getString("学号")+"    "+rs.getString("姓名")+" ");
            }
            sm.close();
            conn.close();
            rs.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;
    }
}

