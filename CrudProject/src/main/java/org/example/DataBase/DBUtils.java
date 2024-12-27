package org.example.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {

    private static final String DB_URL = "jdbc:sqlite:notes.db"; // Путь к базе данных

    public static int getRecordCount() {
        String query = "SELECT COUNT(*) AS count FROM entity"; // SQL-запрос
        int count = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt("count"); // Получаем значение из столбца "count"
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //System.err.println("Ошибка при подсчете записей в базе.");
            DataBaseCreate.createDatabase();
        }

        return count;
    }


}
