package along.mysql.demo;

import java.sql.*;

/**
 * Created by Along on 2017/4/15.
 */
public class Main {
    public static void main(String args[]) {
        Connection coon = null;
        Statement stmt = null;
        try {
            //注册JDBC驱动
            Class.forName(JDBC_DRIVER);

            //打开数据库
            System.out.println("连接数据库...");
            coon = DriverManager.getConnection(DB_URl, USER, PASS);

            //执行查询
            stmt = coon.createStatement();
            String sql;
            sql = "SELECT id,name,url FROM websites";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                //通过字段检索
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");

                //输出数据
                System.out.print("ID：" + id);
                System.out.print(",站点名称：" + name);
                System.out.print(",站点URL：" + url);
                System.out.println();
            }

            //关闭数据库连接
            rs.close();
            stmt.close();
            coon.close();
        } catch (SQLException se) {
            //处理JDBC错误
            se.printStackTrace();
        } catch (Exception e) {
            //处理Class.forName错误
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
                //do nothing
            }

            try {
                if (coon != null) coon.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("程序结束。");
    }

    //JDBC驱动名和数据库URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URl = "jdbc:mysql://localhost:3306/mysql";

    //数据库用户名和密码
    static final String USER = "root";
    static final String PASS = "19980524";
}
