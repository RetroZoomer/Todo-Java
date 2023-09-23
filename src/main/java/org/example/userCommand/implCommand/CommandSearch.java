package org.example.userCommand.implCommand;

import lombok.extern.slf4j.Slf4j;
import org.example.userCommand.Check;
import org.example.userCommand.Helper;
import org.example.userCommand.TaskDAO;
import org.example.userCommand.interfaceCommand.ICommand;

import java.util.Scanner;

@Slf4j
public class CommandSearch extends TaskDAO implements ICommand {
    public CommandSearch(Helper helper, Check check) {
        super(helper, check);
    }

    @Override
    public void realization(Scanner scanner) {
        String line = scanner.nextLine().trim();
        if (!check.hasNextLine(line)){
            return;
        }
        log.debug("{}", line);
        tasks.entrySet()
                .stream()
                .filter(s -> s.getValue().getDescription().contains(line))
                .forEach(CommandPrint::printTask);
    }
}
