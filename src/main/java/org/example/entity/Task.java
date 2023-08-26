package org.example.entity;

import lombok.Data;

@Data
public class Task {
    private boolean done;
    private String description;

    public Task() { }

    public Task(String description) {
        this.description = description;
    }
}


