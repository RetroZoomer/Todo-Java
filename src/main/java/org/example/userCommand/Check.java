package org.example.userCommand;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Task;
import org.example.userCommand.interfaceCheck.HasId;
import org.example.userCommand.interfaceCheck.HasNextLine;
import org.example.userCommand.interfaceCheck.HasTask;

import java.util.Map;
import java.util.Scanner;

@Slf4j
public class Check implements
        HasTask,
        HasNextLine,
        HasId {
    private Helper helper;

    public Check(Helper helper) {
        this.helper = helper;
    }

    @Override
    public boolean hasTask(int id, Map<Integer, Task> tasks) {
        if (id < 0 || !tasks.containsKey(id) ||tasks.get(id).getDescription() == null){
            log.error("task doesn't exist");
            System.err.println("Задачи с таким идентификтором не существует");
            return false;
        }
        return true;
    }

    @Override
    public boolean hasNextLine(String line) {
        boolean hasLine = line.equals(" ");
        if (hasLine) {
            log.error("empty value");
            helper.wrongArgument();
            return false;
        }
        if (line.length() == 0) {
            log.error("empty value");
            helper.wrongArgument();
            return false;
        }
        return true;
    }

    @Override
    public boolean hasId(Scanner scanner) {
        boolean hasId = scanner.hasNextInt();
        if (!hasId) {
            log.error("The task ID is not specified");
            System.err.println("Не указан идентификатор задачи");
            helper.help();
            return false;
        }
        return true;
    }
}
