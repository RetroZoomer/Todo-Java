package org.example;

import lombok.Data;

@Data
public class Task {

    // поля класса
    private boolean done;
    private String description;

    // конструкторы
    public Task() { }

    public Task(String description) {
        this.description = description;
    }
}


