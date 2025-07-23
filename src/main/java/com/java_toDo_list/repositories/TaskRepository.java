package com.java_toDo_list.repositories;

import com.java_toDo_list.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskModel,Long> {
}
