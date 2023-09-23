package org.example.userCommand.implCommand;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Task;
import org.example.userCommand.Check;
import org.example.userCommand.Helper;
import org.example.userCommand.TaskDAO;
import org.example.userCommand.interfaceCommand.ICommand;

import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;
@Slf4j
public class CommandPrint extends TaskDAO implements ICommand {
    public CommandPrint(Helper helper, Check check) {
        super(helper, check);
    }
    @Override
    public void realization(Scanner scanner) {
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
            stream.forEach(CommandPrint::printTask);
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
