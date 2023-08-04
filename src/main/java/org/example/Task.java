package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

import static org.example.Main.ID;
import static org.example.Main.help;

@Getter
@Setter
public class Task {

    // поля класса
    private int id;
    private String done = " ";
    private String description;

    // конструкторы
    public Task() { }

    public Task(String description, Integer id) {
        this.description = description;
        this.id = id;
    }
}


