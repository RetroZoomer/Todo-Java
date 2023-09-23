package org.example.userCommand.implCommand;

import lombok.extern.slf4j.Slf4j;
import org.example.userCommand.Check;
import org.example.userCommand.Helper;
import org.example.userCommand.TaskDAO;
import org.example.userCommand.interfaceCommand.ICommand;

import java.util.Scanner;

@Slf4j
public class CommandDelete extends TaskDAO implements ICommand {
    public CommandDelete(Helper helper, Check check) {
        super(helper, check);
    }

    @Override
    public void realization(Scanner scanner) {
        int id;
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

        delete(id);
    }
}
