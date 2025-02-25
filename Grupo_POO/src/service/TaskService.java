/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.Task;
import repository.TaskRepository;

/**
 *
 * @author HP
 */
public class TaskService {
    private TaskRepository taskRepository;
    
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
}
public List<Task> getPendingTasks(String username) {
        return taskRepository.findPendingTasksByUsername(username);
    }
}
