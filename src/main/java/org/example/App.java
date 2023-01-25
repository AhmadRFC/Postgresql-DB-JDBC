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
            task.updateTask(1, "fooo", true, "Ahmad Alabbasei");
            task.updateTask(2, "bar", false, "Ahmad Alabbasei");
            task.updateTask(3, "eggs", false, "Ahmad Alabbasei");
            task.updateTask(4, "task4", true, "Ahmad Alabbasei");
            task.updateTask(5, "norf", false, "Ahmad Alabbasei");


            // Retrieve all tasks
            task.retrieveTasks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}