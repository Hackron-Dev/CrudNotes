package org.example.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBadd {

    // Путь к базе данных SQLite
    private static final String DB_URL = "jdbc:sqlite:notes.db";

    // Метод для добавления данных в таблицу
    public static void addEntity(String name, String description) {
        String insertSQL = "INSERT INTO entity (id, name, description) VALUES (?, ?, ?)";

        // Соединение с базой данных и выполнение запроса
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            // НАДО чтоб понимало что куда вместо sql кода

            pstmt.setString(2, name);
            pstmt.setString(3, description);

            // Выполнение запроса
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Запись успешно добавлена.");
            } else {
                System.out.println("Не удалось добавить запись.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при добавлении данных в базу.");
        }
    }


}
