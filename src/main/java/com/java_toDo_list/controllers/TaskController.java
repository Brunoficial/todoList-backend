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

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/list")
    public ResponseEntity<List<TaskModel>> getTasks() {
        List<TaskModel> tasks = taskService.listTasks();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/create")
    public ResponseEntity<TaskModel> createTask(@RequestBody TaskDto data) {
        TaskModel newTask = taskService.createTask(data);
        return ResponseEntity.ok(newTask);
    }
}
