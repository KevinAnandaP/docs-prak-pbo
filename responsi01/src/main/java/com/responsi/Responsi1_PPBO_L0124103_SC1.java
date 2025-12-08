package com.responsi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Responsi1_PPBO_L0124103_SC1 {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/todo_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    public Responsi1_PPBO_L0124103_SC1() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            createTableIfNotExists();
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found");
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    private void createTableIfNotExists() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", DB_USER, DB_PASS);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS todo_db");
        } catch (SQLException e) {
            System.err.println("Gagal membuat database: " + e.getMessage());
        }

        String sql = "CREATE TABLE IF NOT EXISTS tasks (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "description VARCHAR(255) NOT NULL, " +
                     "is_done BOOLEAN DEFAULT FALSE)";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error initializing table: " + e.getMessage());
        }
    }

    public List<Responsi1_PPBO_L0124103_SC2> getAllTasks() {
        List<Responsi1_PPBO_L0124103_SC2> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tasks.add(new Responsi1_PPBO_L0124103_SC2(
                    rs.getInt("id"),
                    rs.getString("description"),
                    rs.getBoolean("is_done")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching tasks: " + e.getMessage());
        }
        return tasks;
    }

    public void addTask(String description) {
        String sql = "INSERT INTO tasks (description, is_done) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, description);
            pstmt.setBoolean(2, false);
            pstmt.executeUpdate();
            System.out.println("Task berhasil ditambahkan!");
        } catch (SQLException e) {
            System.err.println("Error adding task: " + e.getMessage());
        }
    }

    public void updateTaskStatus(int id, boolean isDone) {
        String sql = "UPDATE tasks SET is_done = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, isDone);
            pstmt.setInt(2, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Status task diupdate");
            } else {
                System.out.println("Task tidak ditemukan");
            }
        } catch (SQLException e) {
            System.err.println("Error updating task: " + e.getMessage());
        }
    }

    public void deleteTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Task dihapus");
            } else {
                System.out.println("Task tidak ditemukan");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting task: " + e.getMessage());
        }
    }
}
