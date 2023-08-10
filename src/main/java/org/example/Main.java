package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    static boolean helpPrinted = false;
    static int exit = 0;
    static int ID = 0;
    static Map<Integer, Task> tasks = new LinkedHashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger("Main");
    public static void main(String[] args) {
        LOGGER.info("Start project");
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
        if (id < 0 || !tasks.containsKey(id) ||tasks.get(id).getDescription() == null){
            System.err.println("Задачи с таким идентификтором не существует");
            return false;
        }
        return true;
    }
    public static boolean hasNextLine(String line){
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

    public static void add(Scanner scanner) {
        String line = scanner.nextLine().trim();
        if (line.length() == 0) {
            System.err.println("Необходимо ввести описание задачи");
            help();
            return;
        }
        ID++;
        tasks.put(ID, new Task(line));
    }

    public static void print(Scanner scanner) {
        String line = scanner.nextLine().trim();
        boolean all = line.equals("all");
        if (!all && line.length() > 0){
            wrongArgument();
            return;
        }
        Stream<Map.Entry<Integer, Task>> stream = tasks.entrySet().stream();
        if (!all) {
            stream = stream.filter(s -> !s.getValue().isDone());
        }
        stream.forEach(Main::printTask);
    }

    public static void printTask(Map.Entry<Integer, Task> entry) {
        System.out.printf("%s. [%s] %s\n",
                entry.getKey(),
                entry.getValue().isDone() ? "X" : " ",
                entry.getValue().getDescription());
    }

    public static void search(Scanner scanner) {
        String line = scanner.nextLine().trim();
        if (!hasNextLine(line)){
            return;
        }

        tasks.entrySet()
                .stream()
                .filter(s -> s.getValue().getDescription().contains(line))
                .forEach(Main::printTask);
    }

    public static void toggle(Scanner scanner) {
        int id = -1;
        try {
            id = scanner.nextInt();
        } catch (InputMismatchException i){
            System.err.println("Не указан идентификатор задачи");
            LOGGER.error("The task ID is not specified");
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
        tasks.get(id).setDone(!tasks.get(id).isDone());
    }

    public static void delete(Scanner scanner) {
        int id = -1;
        boolean hasId = scanner.hasNextInt();
        if (hasId) {
            id = scanner.nextInt();
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
        tasks.remove(id);
    }

    public static void edit(Scanner scanner) {
        int id = -1;
        boolean hasId = scanner.hasNextInt();
        if (hasId) {
            id = scanner.nextInt();
        }
        if (!hasId) {
            System.err.println("Не указан идентификатор задачи");
            help();
            return;
        }
        String line = scanner.nextLine().trim();
        if (!hasNextLine(line)){
            return;
        }
        if (!hasTask(id)){
            return;
        }
        tasks.get(id).setDescription(line);
    }
}