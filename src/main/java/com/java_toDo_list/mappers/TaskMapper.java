package com.java_toDo_list.mappers;

import com.java_toDo_list.DTOs.TaskDto;
import com.java_toDo_list.models.TaskModel;

public class TaskMapper {
    public static TaskDto mapToTaskDto(TaskModel task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getState()
        );
    }

    public static TaskModel mapToTask(TaskDto taskDto) {
        return new TaskModel(
                taskDto.getId(),
                taskDto.getTitle(),
                taskDto.getDescription(),
                taskDto.getState()
        );
    }
}
