package org.example;

import java.util.Scanner;

import static org.example.Main.help;

public class Task {

    // поля класса
    private int id = 1;
    private String done = " ";
    private String description;

    // конструкторы
    public Task() { }

    // get, set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDone() {
        return description;
    }

    public void setDone(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //методы
    public void add(Scanner scanner) {
        String line = scanner.nextLine().trim();
        if (line.length() == 0) {
            System.err.println("Необходимо ввести описание задачи");
            help();
            return;
        }
    }

    public void print() {
        if (description == null){
            //временная заглушка
            System.out.println("Пока что нет задач");
        } else {
            System.out.println(id + ". " + "[" + done + "] " + description);
        }
    }

    public void toggle(int id) {
        if (description == null){
            //пусто
        }else {
            if (done == " ") {
                done = "X";
            } else {
                done = " ";
            }
        }
    }



}


