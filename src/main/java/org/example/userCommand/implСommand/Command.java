package org.example.userCommand.implСommand;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Task;
import org.example.userCommand.Helper;
import org.example.userCommand.interfaceCommand.CommandAdd;
import org.example.userCommand.interfaceCommand.CommandPrint;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

@Slf4j
public class Command implements CommandAdd,
        CommandPrint {
    private static int id = 0;
    private static Map<Integer, Task> tasks = new LinkedHashMap<>();
    private Helper helper;

    public Command(Helper helper) {
        this.helper = helper;
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
}
