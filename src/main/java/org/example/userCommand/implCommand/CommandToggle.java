package org.example.userCommand.implCommand;

import lombok.extern.slf4j.Slf4j;
import org.example.userCommand.Check;
import org.example.userCommand.Helper;
import org.example.userCommand.TaskDAO;
import org.example.userCommand.interfaceCommand.ICommand;

import java.util.InputMismatchException;
import java.util.Scanner;

@Slf4j
public class CommandToggle extends TaskDAO implements ICommand {
    public CommandToggle(Helper helper, Check check) {
        super(helper, check);
    }

    @Override
    public void realization(Scanner scanner) {
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
}
