package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.application.Application;
import org.example.userCommand.*;
import org.example.userCommand.implCommand.*;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Helper helper = new Helper();
        Check check = new Check(helper);
        CommandAdd commandAdd = new CommandAdd(helper, check);
        CommandPrint commandPrint = new CommandPrint(helper, check);
        CommandToggle commandToggle = new CommandToggle(helper, check);
        CommandSearch commandSearch = new CommandSearch(helper, check);
        CommandDelete commandDelete = new CommandDelete(helper, check);
        CommandEdit commandEdit = new CommandEdit(helper, check);

        Application application = new Application(
                commandAdd, commandPrint, commandToggle,
                commandSearch, commandDelete, commandEdit,
                helper);
        application.applicationStart();
    }
}