package org.example.userCommand;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Task;
import org.example.userCommand.interfaceCommand.*;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

@Slf4j
public class Command implements
        CommandAdd,
        CommandPrint,
        CommandToggle,
        CommandSearch,
        CommandDelete,
        CommandEdit {
    private static int id = 0;
    private static Map<Integer, Task> tasks = new LinkedHashMap<>();
    private Helper helper;
    private Check check;

    public Command(Helper helper, Check check) {
        this.helper = helper;
        this.check = check;
    }

    @Override
    public void add(Scanner scanner) {
        String line = scanner.nextLine().trim();
        if (line.length() == 0) {
            log.error("The task description is empty");
            System.err.println("Необходимо ввести описание задачи");
            helper.help();
            return;
        }
        id++;
        tasks.put(id, new Task(line));
        log.debug("{}", line);
    }

    @Override
    public void print(Scanner scanner) {
            String line = scanner.nextLine().trim();
            boolean all = line.equals("all");
            if (!all && line.length() > 0){
                helper.wrongArgument();
                return;
            }
            Stream<Map.Entry<Integer, Task>> stream = tasks.entrySet().stream();
            if (!all) {
                stream = stream.filter(s -> !s.getValue().isDone());
            }
            log.debug(all ? "all" : "");
            stream.forEach(Command::printTask);
    }

    public static void printTask(Map.Entry<Integer, Task> entry) {
        String out = String.format("%s. [%s] %s",
                entry.getKey(),
                entry.getValue().isDone() ? "X" : " ",
                entry.getValue().getDescription());
        System.out.println(out);
        log.debug("User output: {}", out);
    }

    @Override
    public void toggle(Scanner scanner) {
        int id;
        try {
            id = scanner.nextInt();
        } catch (InputMismatchException ex){
            System.err.println("Не указан идентификатор задачи");
            log.error("The task ID is not specified: ", ex);
            helper.help();
            return;
        }
        String tail = scanner.nextLine().trim();
        if (tail.length() != 0) {
            helper.wrongArgument();
            return;
        }
        if (!check.hasTask(id, tasks)){
            return;
        }
        tasks.get(id).setDone(!tasks.get(id).isDone());
        log.debug("{}", id);
    }

    @Override
    public void search(Scanner scanner) {
        String line = scanner.nextLine().trim();
        if (!check.hasNextLine(line)){
            return;
        }
        log.debug("{}", line);
        tasks.entrySet()
                .stream()
                .filter(s -> s.getValue().getDescription().contains(line))
                .forEach(Command::printTask);
    }

    @Override
    public void delete(Scanner scanner) {
        int id = -1;
        if (check.hasId(scanner)) {
            id = scanner.nextInt();
        } else {return;}

        String tail = scanner.nextLine().trim();
        if (tail.length() != 0) {
            helper.wrongArgument();
            return;
        }

        if (!check.hasTask(id, tasks)){
            return;
        }

        tasks.remove(id);
        log.debug("{}", id);
    }

    @Override
    public void edit(Scanner scanner) {
        int id = -1;
        if (check.hasId(scanner)) {
            id = scanner.nextInt();
        } else {return;}

        String line = scanner.nextLine().trim();
        if (!check.hasNextLine(line)){
            return;
        }

        if (!check.hasTask(id, tasks)){
            return;
        }

        tasks.get(id).setDescription(line);
        log.debug("{} {}", id, line);
    }
}
