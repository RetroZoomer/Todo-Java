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
        description = line;
    }

    public void print(Scanner scanner) {
        String line = scanner.nextLine().trim();
        boolean all = line.equals("all");
        if (!all && line.length() > 0){
            wrongArgument();
            return;
        }
        if (description != null && done != " " || all) {
            System.out.println(id + ". " + "[" + done + "] " + description);
        }
    }

    public void search(Scanner scanner) {
        String line = scanner.nextLine().trim();
        if (line.length() == 0) {
            System.err.println("Необходимо ввести описание задачи");
            help();
            return;
        }
        // поиск по коллекции
    }

    public void toggle(Scanner scanner) {
        int id = -1;
        boolean hasId = scanner.hasNextInt();
        if (hasId) {
            id = scanner.nextInt();
        }
        String tail = scanner.nextLine().trim();
        if (tail.length() != 0) {
            wrongArgument();
            return;
        }
        if (!hasId) {
            System.err.println("Не указан идентификатор задачи");
            help();
        }
        if (description == null || id != this.id){
            System.err.println("Задачи с таким идентификтором не существует");
            return;
        }
        if (done == " ") {
            done = "X";
        } else {
            done = " ";
        }

    }

    public void delete(Scanner scanner) {
        String line = scanner.nextLine().trim();
        if (line.length() == 0) {
            System.err.println("Необходимо ввести иденщтификатор задачи");
            help();
            return;
        }
        // удаление из коллекции
    }

    public void edit(Scanner scanner) {
        String line = scanner.nextLine().trim();
        if (line.length() == 0) {
            System.err.println("Необходимо ввести иденщтификатор задачи");
            help();
            return;
        }
        // изменение элемента в коллекции
    }

    private static void wrongArgument() {
        System.err.println("Недопустимый аргумент команды");
        help();
    }


}


