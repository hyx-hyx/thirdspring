package com.example.thirdspring;

import com.example.thirdspring.connectSql;
import com.example.thirdspring.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//导入的操作类
public class importSql {
    private static String stumark = null;//用于存放随机得出的记录的学号
    private static String stuname = null;//用于存放随机得出的记录的姓名
    static String creatsql = "CREATE TABLE if not exists `excelstudent`(" + "\n"
            + "`student_id` int unsigned auto_increment," + "\n"
            + "`学号` varchar(100) not null," + "\n"
            + "`姓名` varchar(100) not null," + "\n"
            + "primary key(`student_id`)" + "\n"
            + ")ENGINE=InnoDB DEFAULT CHARSET=utf8;" + "\n";
    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //指定连接数据库的url
    final static String DB_URL = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
    //mysql用户名
    final static String name = "root";
    //mysql密码
    final static String pwd = "123456";

    public static void connect() {
        Connection conn = null;
        Statement stmt = null;
        try {
            //注册jdbc驱动
            Class.forName(JDBC_DRIVER);
            //打开连接
            System.out.println("连接数据库");
            conn = DriverManager.getConnection(DB_URL, name, pwd);
            //执行创建表
            System.out.println("创建表");
            stmt = conn.createStatement();
            if (0 == stmt.executeLargeUpdate(creatsql)) {
                System.out.println("成功创建表！");
            } else {
                System.out.println("创建表失败！");
            }
            stmt.close();
            conn.close();
            System.out.println("关闭资源");
        } catch (Exception e) {
            System.out.println("创建表失败！");
            e.printStackTrace();
        }
    }

    public void insert(String mark, String stuname)//导入学号
    {
        connectSql sql = new connectSql();
        Connection conn = sql.getconnection();
        try {
            Statement sm = conn.createStatement();
            //向已有的表中插入学号，id会自动增加
            String sqlstatement = "insert into excelstudent (学号,姓名) values(\'" + mark + "\'" + "," + "\'" + stuname + "\')";
            sm.executeUpdate(sqlstatement);
            sm.close();
            conn.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

