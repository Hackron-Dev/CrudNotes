package org.example.functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Filters {

    private static final String DB_URL = "jdbc:sqlite:notes.db";

    // Фильтр по ID с учетом search
    public static String[] FilterByID(String search) {
        String query;
        if (search == null || search.isEmpty()) {
            query = "SELECT * FROM entity ORDER BY id"; // Без фильтрации
        } else {
            query = "SELECT * FROM entity WHERE name LIKE ? ORDER BY id"; // С фильтрацией по имени
        }
        return getFilteredData(query, search);
    }

    // Фильтр по длине текста (description) с учетом search
    public static String[] FilterByTextLength(String search) {
        String query;
        if (search == null || search.isEmpty()) {
            query = "SELECT * FROM entity ORDER BY LENGTH(description)"; // Без фильтрации
        } else {
            query = "SELECT * FROM entity WHERE name LIKE ? ORDER BY LENGTH(description)"; // С фильтрацией по имени
        }
        return getFilteredData(query, search);
    }

    // Фильтр по времени обновления (updated_at) с учетом search
    public static String[] FilterByUpdateTime(String search) {
        String query;
        if (search == null || search.isEmpty()) {
            query = "SELECT * FROM entity ORDER BY updated_at"; // Без фильтрации
        } else {
            query = "SELECT * FROM entity WHERE name LIKE ? ORDER BY updated_at"; // С фильтрацией по имени
        }
        return getFilteredData(query, search);
    }

    // Общий метод для получения данных
    private static String[] getFilteredData(String query, String search) {
        ArrayList<String> results = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {


            if (search != null && !search.isEmpty()) {
                pstmt.setString(1, "%" + search + "%"); // LIKE '%search%'
            }


            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String createdAt = rs.getString("created_at");
                    String updatedAt = rs.getString("updated_at");

                    // Формируем строку с данными
                    results.add(String.format("ID: %d, Name: %s, Description: %s, Created At: %s, Updated At: %s",
                            id, name, description, createdAt, updatedAt));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при выполнении фильтрации данных.");
        }

        // Ретурн массив строк
        return results.toArray(new String[0]);
    }
}
