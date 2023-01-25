package org.example;

import org.example.DBConnection;

import java.sql.*;

public class Task {
    private String name;
    private boolean isComplete;

    public Task(String name, boolean isComplete) {
        this.name = name;
        this.isComplete = isComplete;
    }
    public Task() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public void insertTask() {
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            PreparedStatement insertStmt =
                    dbConnection.prepareStatement("INSERT INTO todo (task, done) VALUES (?, ?);");
            insertStmt.setString(1, this.name);
            insertStmt.setBoolean(2, (this.isComplete));
            int rows = insertStmt.executeUpdate();
            System.out.println("Rows affected: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retrieveTasks() {
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            String query = "SELECT serial, task, done FROM todo";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //Display values
                String row =
                        "Serial: " + rs.getInt("serial") + " org.example.Task: " + rs.getString(
                                "task") + " Done: " + rs.getBoolean("done") + " Owner: " + rs.getString("Owner") + "\n";
                System.out.print(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateTask(int serial, String updateTaskName, boolean doneOrNot, String owner) {
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            PreparedStatement updateStmt =
                    dbConnection.prepareStatement("UPDATE todo SET task = ?, done = ?, owner ? WHERE serial = ?");
            updateStmt.setString(1, updateTaskName);
            updateStmt.setBoolean(2, doneOrNot);
            updateStmt.setString(3, owner);
            updateStmt.setInt(4, serial);
            int row = updateStmt.executeUpdate();
            System.out.println("Rows updated: " + row);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(int serial) {
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            PreparedStatement deleteStmt =
                    dbConnection.prepareStatement("DELETE FROM todo WHERE serial = ?");
            deleteStmt.setInt(1, serial);
            int row = deleteStmt.executeUpdate();
            System.out.println("Rows updated: " + row);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "org.example.Task: " + this.name + "\nDone: " + (this.isComplete ? "1" : "0");
    }
}
