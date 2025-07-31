package com.java_toDo_list.services;

import com.java_toDo_list.DTOs.TaskDto;
import com.java_toDo_list.mappers.TaskMapper;
import com.java_toDo_list.models.TaskModel;
import com.java_toDo_list.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;


import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Transactional
    public ResponseEntity<List<TaskDto>> listTasks() {
        List<TaskDto> tasks =  taskRepository.findAll()
                .stream()
                .map((task) -> TaskMapper.mapToTaskDto(task))
                .toList();

        return ResponseEntity.ok(tasks);
    }

    @Transactional
    public ResponseEntity<TaskDto> createTask(TaskDto data) {
        if (data.getConcluded() == null) {data.setConcluded(false);}
        TaskModel newTask = TaskMapper.mapToTaskModel(data);
        taskRepository.save(newTask);

        TaskDto newTaskDto = TaskMapper.mapToTaskDto(newTask);
        return ResponseEntity.ok(newTaskDto);
    }

    public ResponseEntity<Void> deleteTask(Long id) {
        Optional<TaskModel> task = taskRepository.findById(id);
        if (task.isEmpty()) return ResponseEntity.notFound().build();

        taskRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity<TaskDto> updateTask(TaskDto data, Long id) throws Exception{
        TaskModel taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

        TaskModel taskSource = TaskMapper.mapToTaskModel(data);
        BeanUtils.copyProperties(taskSource, taskEntity, "id");

        /*
        Field[] taskFields = taskEntity.getClass().getDeclaredFields();
        Field[] dtoFields = data.getClass().getDeclaredFields();
        for (Field dtoField : dtoFields ) {
            dtoField.setAccessible(true);
            Object newValue = dtoField.get(data);

            if (newValue != null) {
                for (Field taskField : taskFields) {
                    if (taskField.getName().equals(dtoField.getName())) {
                        taskField.setAccessible(true);
                        taskField.set(taskEntity, newValue);
                    }
                }
            }
        }
         */

        TaskModel updatedTask = taskRepository.save(taskEntity);
        TaskDto uptadedTaskDto = TaskMapper.mapToTaskDto(updatedTask);
        return ResponseEntity.ok(uptadedTaskDto);
    }
}
