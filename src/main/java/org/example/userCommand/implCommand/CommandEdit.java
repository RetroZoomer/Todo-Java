package org.example.userCommand.implCommand;

import lombok.extern.slf4j.Slf4j;
import org.example.userCommand.Check;
import org.example.userCommand.Helper;
import org.example.userCommand.TaskDAO;
import org.example.userCommand.interfaceCommand.ICommand;

import java.util.Scanner;

@Slf4j
public class CommandEdit extends TaskDAO implements ICommand {
    public CommandEdit(Helper helper, Check check) {
        super(helper, check);
    }

    @Override
    public void realization(Scanner scanner) {
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
