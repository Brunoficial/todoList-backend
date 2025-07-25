package com.java_toDo_list.services;

import com.java_toDo_list.DTOs.TaskDto;
import com.java_toDo_list.mappers.TaskMapper;
import com.java_toDo_list.models.TaskModel;
import com.java_toDo_list.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;


import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Transactional
    public ResponseEntity<List<TaskModel>> listTasks() {
        List<TaskModel> tasks =  taskRepository.findAll();
        return ResponseEntity.ok(tasks);
    }

    @Transactional
    public ResponseEntity<TaskModel> createTask(TaskDto data) {
        TaskModel newTask = TaskMapper.mapToTaskModel(data);
        taskRepository.save(newTask);

        return ResponseEntity.ok(newTask);
    }

    public ResponseEntity<Void> deleteTask(Long id) {
        taskRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public TaskModel updateTask(TaskDto data, Long id) {
        TaskModel task = taskRepository.findById(id);
         Field[] campos = task.getClass().getDeclaredFields();
         for (Field campo : campos) {
             campo.setAccessible(true);
         }
    }
}
