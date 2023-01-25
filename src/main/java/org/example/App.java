package org.example;

import java.sql.SQLException;
public class App
{
    public static void main(String[] args) {
        try {
            DBConnection db = DBConnection.getInstance();

            Task task = new Task();

//            task.deleteTask(6);
            //Update
            task.updateTask(2, "fee foo", true);

            // Retrieve all tasks
            task.retrieveTasks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}