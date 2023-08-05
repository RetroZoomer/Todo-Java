package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static boolean helpPrinted = false;
    static int exit = 0;
    static int ID = 0;
    static List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        while (exit != 1) {
            String choice = console.next();
            choice = choice.replaceAll("\\s+","");
            switch (choice) {
                case ("add") -> {
                    System.out.println();
                    add(console);
                }
                case ("print") -> {
                    System.out.println();
                    print(console);
                }
                case ("toggle") -> {
                    System.out.println();
                    toggle(console);
                }
                case ("search") -> {
                    System.out.println();
                    search(console);
                }
                case ("delete") -> {
                    System.out.println();
                    delete(console);
                }
                case ("edit") -> {
                    System.out.println();
                    edit(console);
                }
                case ("quit") -> {
                    exit = 1;
                    System.out.println("Завершение работы!");
                }
                default -> {
                    System.err.println("Возможно вы ввели не существующую комманду или опечатались. Попробуйте снова :)");
                    help();
                }
            }

        }

    }
    public static void help() {
        if (helpPrinted) {
            return;
        }
        System.out.println("""
                Возможные команды:\s
                \t add <описание задачи>\s
                \t print [all]
                \t toggle <идентификатор задачи>
                \t search <substring>
                \t delete <идентификатор задачи>
                \t edit <идентификатор задачи> <новое значение>
                \t quit""");
        helpPrinted = true;
    }

    private static void wrongArgument() {
        System.err.println("Недопустимый аргумент команды");
        help();
    }
    public static boolean hasTask(int id) {
        if (id < 0 || taskList.get(id).getDescription() == null || id != taskList.get(id).getId() - 1){
            System.err.println("Задачи с таким идентификтором не существует");
            return false;
        }
        return true;
    }

    public static void add(Scanner scanner) {
        String line = scanner.nextLine().trim();
        if (line.length() == 0) {
            System.err.println("Необходимо ввести описание задачи");
            help();
            return;
        }
        ID++;
        taskList.add(new Task(line, ID));
    }

    public static void print(Scanner scanner) {
        String line = scanner.nextLine().trim();
        boolean all = line.equals("all");
        if (!all && line.length() > 0){
            wrongArgument();
            return;
        }
        for (Task task: taskList) {
            if (task.getDescription() != null && !Objects.equals(task.getDone(), " ") || all) {
                System.out.println(task.getId() + ". " + "[" + task.getDone() + "] " + task.getDescription());
            }
        }
    }

    public static void toggle(Scanner scanner) {
        int id = -1;
        boolean hasId = scanner.hasNextInt();
        if (hasId) {
            id = scanner.nextInt() - 1;
        }
        if (!hasId) {
            System.err.println("Не указан идентификатор задачи");
            help();
            return;
        }
        String tail = scanner.nextLine().trim();
        if (tail.length() != 0) {
            wrongArgument();
            return;
        }
        if (!hasTask(id)){
            return;
        }
        if (Objects.equals(taskList.get(id).getDone(), " ")) {
            taskList.get(id).setDone("X");
        } else {
            taskList.get(id).setDone(" ");
        }
    }

    public static void delete(Scanner scanner) {
        int id = -1;
        boolean hasId = scanner.hasNextInt();
        if (hasId) {
            id = scanner.nextInt() - 1;
        }
        if (!hasId) {
            System.err.println("Не указан идентификатор задачи");
            help();
            return;
        }
        String tail = scanner.nextLine().trim();
        if (tail.length() != 0) {
            wrongArgument();
            return;
        }

        taskList.remove(id);
    }

    public static void edit(Scanner scanner) {
        int id = -1;
        boolean hasId = scanner.hasNextInt();
        if (hasId) {
            id = scanner.nextInt() - 1;
        }
        if (!hasId) {
            System.err.println("Не указан идентификатор задачи");
            help();
            return;
        }
        String line;
        boolean hasLine = scanner.hasNextLine();
        if (hasLine) {
            line = scanner.nextLine().trim();
        } else {
            wrongArgument();
            return;
        }
        if (line.length() == 0) {
            wrongArgument();
            return;
        }
        if (!hasTask(id)){
            return;
        }
        taskList.get(id).setDescription(line);
    }

    public static void search(Scanner scanner) {
        String line = scanner.nextLine().trim();

        if (!hasNextLine(scanner, line)){
            return;
        }
        String finalLine = line;
        boolean startsWith =
        taskList
                .stream()
                .anyMatch(s -> s.getDescription().startsWith(finalLine));
        if (!startsWith){
            System.err.println("Такой(-их) задач не найдено");
        } else {
            taskList
                    .stream()
                    .filter(s -> s.getDescription().startsWith(finalLine))
                    .map(s -> s.getId() + ". [" + s.getDone() + "] " + s.getDescription())
                    .forEach(System.out::println);
        }
    }

    public static boolean hasNextLine(Scanner scanner, String line){
        boolean hasLine = line.equals(" ");
        if (hasLine) {
            wrongArgument();
            return false;
        }
        if (line.length() == 0) {
            wrongArgument();
            return false;
        }
        return true;
    }
}