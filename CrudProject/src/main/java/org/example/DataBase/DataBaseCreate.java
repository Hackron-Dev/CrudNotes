package org.example.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBaseCreate {

    private static final String DB_URL = "jdbc:sqlite:notes.db"; // Имя файла базы данных
    //private static final String DB_URL = "jdbc:sqlite:" + System.getProperty("user.dir") + "/notes.db";

    public static void createDatabase() {
        String createTableSQL =
                "CREATE TABLE IF NOT EXISTS entity (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + // UUID в виде строки
                "name TEXT, " +
                "description TEXT, " + // Ограничение на 255 символов
                "created_at TEXT DEFAULT CURRENT_TIMESTAMP, " + // Автоматическая установка времени создания
                "updated_at TEXT DEFAULT CURRENT_TIMESTAMP " + // Время обновления
                ");";

        String createTriggerSQL =
                "CREATE TRIGGER IF NOT EXISTS update_updated_at " +
                "AFTER UPDATE ON entity " +
                "FOR EACH ROW " +
                "BEGIN " +
                "   UPDATE entity SET updated_at = CURRENT_TIMESTAMP WHERE id = OLD.id;" +
                "END;";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            // Создание таблицы
            stmt.execute(createTableSQL);
            System.out.println("Таблица успешно создана или уже существует.");

            // Создание триггера для обновления поля updated_at
            stmt.execute(createTriggerSQL);
            System.out.println("Триггер для обновления updated_at успешно создан.");

        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println("Ошибка при создании базы данных.");
        }
    }
}
