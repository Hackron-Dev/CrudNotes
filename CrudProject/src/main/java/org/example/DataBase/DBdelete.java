package org.example.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBdelete {

    private static final String DB_URL = "jdbc:sqlite:notes.db"; // Путь к базе данных

    // Метод для удаления записи по id
    public static void deleteEntityById(int id) {
        // SQL запрос для проверки существования записи с таким id
        String checkSQL = "SELECT COUNT(*) FROM entity WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement checkStmt = conn.prepareStatement(checkSQL)) {

            // Устанавливаем значение id в запрос
            checkStmt.setInt(1, id);

            // Выполнение запроса для проверки наличия записи с таким id
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // Если запись существует, выполняем удаление
                String deleteSQL = "DELETE FROM entity WHERE id = ?";
                try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSQL)) {
                    deleteStmt.setInt(1, id);
                    int rowsAffected = deleteStmt.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Запись с id " + id + " успешно удалена.");
                    } else {
                        System.out.println("Не удалось удалить запись.");
                    }
                }
            } else {
                System.out.println("Запись с id " + id + " не найдена.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при работе с базой данных.");
        }
    }


}
