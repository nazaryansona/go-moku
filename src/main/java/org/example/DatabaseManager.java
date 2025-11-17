package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Singleton class to manage SQLite database connection and saving game results.
 */
public class DatabaseManager {
    private static DatabaseManager instance;
    private Connection connection;

    private DatabaseManager() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:gomoku.db");
            connection.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS game_results (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "winner TEXT, " +
                            "rows INTEGER, " +
                            "cols INTEGER, " +
                            "played_at DATETIME DEFAULT CURRENT_TIMESTAMP)"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void saveResult(String winner, int rows, int cols) {
        try {
            String sql = "INSERT INTO game_results (winner, rows, cols) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, winner);
            stmt.setInt(2, rows);
            stmt.setInt(3, cols);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
