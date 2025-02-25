/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

/**
 *
 * @author HP
 */
import java.util.List;
import model.Task;

public class TaskRepository {
    List<Task> findPendingTasksByUsername(String username);
    
}
