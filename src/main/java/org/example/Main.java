package org.example;

import java.util.ArrayList;
import java.util.List;
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
                case ("add"):
                    System.out.println();
                    add(console);
                    break;
                case ("print"):
                    System.out.println();
                    print(console);
                    break;
                case ("toggle"):
                    System.out.println();
                    toggle(console);
                    break;
                case ("search"):
                    System.out.println();
                    //task.search(console);
                    System.out.println();
                    break;
                case ("delete"):
                    System.out.println();
                    delete(console);
                    break;
                case ("edit"):
                    System.out.println();
                    //task.edit(console);
                    System.out.println();
                    break;
                case ("quit"):
                    exit = 1;
                    System.out.println("Завершение работы!");
                    break;
                default:
                    System.err.println("Возможно вы ввели не существующую комманду или опечатались. Попробуйте снова :)");
                    help();
                    break;
            }

        }

    }
    public static void help() {
        if (helpPrinted) {
            return;
        }
        System.out.println("Возможные команды: \n" +
                "\tadd <описание задачи> \n" +
                "\tprint [all]\n" +
                "\tsearch\n" +
                "\ttoggle <идентификатор задачи>\n" +
                "\tdelete <идентификатор задачи>\n" +
                "\tedit <идентификатор задачи> <новое значение>\n" +
                "\tquit");
        helpPrinted = true;
    }

    private static void wrongArgument() {
        System.err.println("Недопустимый аргумент команды");
        help();
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
            if (task.getDescription() != null && task.getDone() != " " || all) {
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
        if (id < 0 || taskList.get(id).getDescription() == null || id != taskList.get(id).getId() - 1){
            System.err.println("Задачи с таким идентификтором не существует");
            return;
        }
        if (taskList.get(id).getDone() == " ") {
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
}