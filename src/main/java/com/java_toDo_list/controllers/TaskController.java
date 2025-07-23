package com.java_toDo_list.controllers;

import com.java_toDo_list.DTOs.TaskDto;
import com.java_toDo_list.models.TaskModel;
import com.java_toDo_list.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/list")
    public List<TaskModel> getTasks() {
        return taskService.listTasks();
    }
}
