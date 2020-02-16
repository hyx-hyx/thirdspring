package com.example.thirdspring;
import java.sql.Connection;
import java.sql.DriverManager;
//数据库操作类
public class connectSql {
    public Connection getconnection()
    {
        Connection conn=null;
        String url ="jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
        String user = "root";
        String password="123456";
        //1.加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2.连接数据库
            conn = DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }
}
