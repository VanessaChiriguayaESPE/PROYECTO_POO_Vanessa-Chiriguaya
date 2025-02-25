package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class Task {
    private String username; // Para vincular la tarea al usuario
    private String description;
    private String status;
    private String dueDate;
    
    public Task(String username, String description, String status, String dueDate) {
        this.username = username;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
    }
    
    // Getters y setters
    public String getUsername() { return username; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public String getDueDate() { return dueDate; }
}
