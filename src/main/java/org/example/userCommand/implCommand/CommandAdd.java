package org.example.userCommand.implCommand;

import lombok.extern.slf4j.Slf4j;
import org.example.userCommand.Check;
import org.example.userCommand.Helper;
import org.example.userCommand.TaskDAO;
import org.example.userCommand.interfaceCommand.ICommand;

import java.util.Scanner;

@Slf4j
public class CommandAdd extends TaskDAO implements ICommand {
    public CommandAdd(Helper helper, Check check) {
        super(helper, check);
    }
    @Override
    public void realization(Scanner scanner) {
        String line = scanner.nextLine().trim();
        if (line.length() == 0) {
            log.error("The task description is empty");
            System.err.println("Необходимо ввести описание задачи");
            helper.help();
            return;
        }
        save(line);
    }
}
