package org.example.application;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.userCommand.Command;
import org.example.userCommand.Helper;

import java.util.Scanner;
@Slf4j
@Data
public class Application {
    private static boolean exit = false;

    private Command command;
    private Helper helper;

    public Application(Command command, Helper helper) {
        this.command = command;
        this.helper = helper;
    }

    public void applicationStart(){
        log.info("START APPLICATION");
        Scanner console = new Scanner(System.in);
        while (!exit) {
            String choice = console.next();
            choice = choice.replaceAll("\\s+","");
            switch (choice) {
                case ("add") -> {
                    System.out.println();
                    command.add(console);
                }
                case ("print") -> {
                    System.out.println();
                    command.print(console);
                }
                case ("toggle") -> {
                    System.out.println();
                    command.toggle(console);
                }
                case ("search") -> {
                    System.out.println();
                    command.search(console);
                }
                case ("delete") -> {
                    System.out.println();
                    command.delete(console);
                }
                case ("edit") -> {
                    System.out.println();
                    command.edit(console);
                }
                case ("quit") -> {
                    exit = true;
                    System.out.println("Завершение работы!");
                    log.debug("quit");
                }
                default -> {
                    System.err.println("Возможно вы ввели не существующую комманду или опечатались. Попробуйте снова :)");
                    helper.help();
                }
            }
        }
    }
}
