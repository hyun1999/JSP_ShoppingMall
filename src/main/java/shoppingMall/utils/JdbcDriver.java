package shoppingMall.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;

public class JdbcDriver {
    /*
        Hikari 사용 버전
     */
    private static HikariDataSource dataSource;

    static {
        try {
            Properties prop = new Properties();
            prop.load(JdbcDriver.class.getClassLoader().getResourceAsStream("db.properties"));

            // HikariCP 설정
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(prop.getProperty("jdbc.url"));
            config.setUsername(prop.getProperty("jdbc.user"));
            config.setPassword(prop.getProperty("jdbc.password"));
            config.setDriverClassName("oracle.jdbc.OracleDriver");
            config.setMaximumPoolSize(2);
            config.setMinimumIdle(1);
            config.setIdleTimeout(10000);

            dataSource = new HikariDataSource(config);
            System.out.println("HikariCP 초기화 완료");

        } catch (IOException e) {
            throw new RuntimeException("DB 설정 파일 로딩 실패", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close() {
        if (dataSource != null) {
            dataSource.close();
            System.out.println("커넥션 풀 종료");
        }
    }
}
