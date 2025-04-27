package shoppingMall.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcDriver {

    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("JDBC 드라이버 로드 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC 드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        Properties prop = new Properties();

        try {
            prop.load(JdbcDriver.class.getClassLoader().getResourceAsStream("WEB-INF/db.properties"));

            String url = prop.getProperty("jdbc.url");
            System.out.println(url);
            String user = prop.getProperty("jdbc.user");
            String password = prop.getProperty("jdbc.password");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("DB 연결 성공");

        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 실패: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("DB 연결 종료");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
