package com.java_toDo_list.services;

import com.java_toDo_list.DTOs.TaskDto;
import com.java_toDo_list.mappers.TaskMapper;
import com.java_toDo_list.models.TaskModel;
import com.java_toDo_list.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;


    public List<TaskModel> listTasks() {
        return taskRepository.findAll();
    }
}
