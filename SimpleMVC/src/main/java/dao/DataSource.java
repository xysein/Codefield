package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DataSource {

    default Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:h2:mem:default", "", "");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
