package org.example;


import java.sql.*;

public class Task {
    private String title;
    private boolean isComplete;
    private String madeby;

    public Task(String title, boolean isComplete, String madeby) {
        this.title = title;
        this.isComplete = isComplete;
        this.madeby = madeby;
    }

    public Task() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public void setMadeby(String madeby) {
        this.madeby = madeby;
    }

    public String getMadeby() {
        return madeby;
    }


    public void insertTask() {
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            PreparedStatement insertStmt = dbConnection.prepareStatement(
                    "INSERT INTO todo (task, done, madeby) VALUES (?, ?, ?);");
            insertStmt.setString(1, this.title);
            insertStmt.setBoolean(2, this.isComplete);
            insertStmt.setString(3, this.madeby);
            int rows = insertStmt.executeUpdate();
            System.out.println("Rows affected: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retrieveTasks() {
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT serial, task, done, madeby FROM todo");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                //Display values
                String row = "Serial: " + rs.getInt("serial") + ", Task: " + rs.getString("task")
                        + ", Done: " + rs.getBoolean("done") + ", madeby: " + rs.getString("madeby")
                        + "\n";
                System.out.print(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateTask(int serial, String updateTaskName, boolean doneOrNot, String madeby) {
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            PreparedStatement updateStmt = dbConnection.prepareStatement(
                    "UPDATE todo SET task = ?, done = ?, madeby = ? WHERE serial = ?");
            updateStmt.setString(1, updateTaskName);
            updateStmt.setBoolean(2, doneOrNot);
            updateStmt.setString(3, madeby);
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
        return "org.example.Task: " + this.title + "\nDone: " + (this.isComplete ? "1" : "0");
    }
}