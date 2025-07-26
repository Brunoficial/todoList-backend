package com.java_toDo_list.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tasks")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    private String description;
    private Boolean concluded;

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof TaskModel))
            return false;
        return super.equals(obj);
    }

    public void update(TaskModel task){
        this.setTitle(task.getTitle());
    }
}
