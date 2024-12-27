package org.example.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBupdate {

    private static final String DB_URL = "jdbc:sqlite:notes.db"; // Путь к базе данных

    // Метод для обновления записи по id
    public static void updateEntityById(int id, String name, String description) {
        // SQL запрос для обновления записи по id
        String updateSQL = "UPDATE entity SET name = ?, description = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            // Устанавливаем значения в запрос
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setInt(3, id);

            // Выполнение запроса на обновление
            int rowsAffected = pstmt.executeUpdate();
            //if (rowsAffected > 0) {
            //    System.out.println("Запись с id " + id + " успешно обновлена.");
            //} else {
            //    System.out.println("Запись с id " + id + " не найдена.");
            //}

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при обновлении записи.");
        }
    }

}
