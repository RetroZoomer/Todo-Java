package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.application.Application;
import org.example.userCommand.Check;
import org.example.userCommand.Command;
import org.example.userCommand.Helper;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Helper helper = new Helper();
        Check check = new Check(helper);
        Command command = new Command(helper, check);

        Application application = new Application(command, helper);
        application.applicationStart();
    }
}