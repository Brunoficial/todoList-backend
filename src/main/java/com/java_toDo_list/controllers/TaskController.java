package com.java_toDo_list.controllers;

import com.java_toDo_list.DTOs.TaskDto;
import com.java_toDo_list.mappers.TaskMapper;
import com.java_toDo_list.models.TaskModel;
import com.java_toDo_list.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/list")
    public ResponseEntity<List<TaskDto>> getTasks() { return taskService.listTasks(); }

    @PostMapping("/create")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto data) {
        return taskService.createTask(data);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto data) throws Exception {
        return taskService.updateTask(data, id);
    }
}
