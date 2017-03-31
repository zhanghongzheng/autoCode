package cn.dev.hub.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by suzunshou on 2017/3/27.
 */
public class DBUtils {
    //实例化Properties
    private static Properties properties = new Properties();

    //使配置文件只加载一次
    static {
        try {
            InputStream in = DBUtils.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //MySQL数据库驱动
    private static String driver = properties.getProperty("driver").trim();
    //MySQL数据库主机地址
    private static String url = properties.getProperty("url").trim();
    //MySQL数据库用户名
    private static String username = properties.getProperty("username").trim();
    //MySQL数据库密码
    private static String password = properties.getProperty("password").trim();
    //数据库名称
    public static String dbName = url.substring(url.lastIndexOf("/") + 1, url.indexOf("?"));

    //禁止new，阻止其被创建实例
    private DBUtils() {

    }

    //使驱动只注册一次
    static {
        try {
            //加载驱动
            try {
                Class.forName(driver).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //用来解决建立连接，方便修改，实现动态连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    //用来释放连接
    public static void closeDB(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
