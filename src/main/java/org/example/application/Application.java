package org.example.application;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.userCommand.*;
import org.example.userCommand.implCommand.*;

import java.util.Scanner;
@Slf4j
@Data
public class Application {
    private static boolean exit = false;
    private CommandAdd commandAdd;
    private CommandPrint commandPrint;
    private CommandToggle commandToggle;
    private CommandSearch commandSearch;
    private CommandDelete commandDelete;
    private CommandEdit commandEdit;
    private Helper helper;

    public Application(
            CommandAdd commandAdd, CommandPrint commandPrint,
            CommandToggle commandToggle, CommandSearch commandSearch,
            CommandDelete commandDelete, CommandEdit commandEdit,
            Helper helper) {
        this.commandAdd = commandAdd;
        this.commandPrint = commandPrint;
        this.commandToggle = commandToggle;
        this.commandSearch = commandSearch;
        this.commandDelete = commandDelete;
        this.commandEdit = commandEdit;
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
                    commandAdd.realization(console);
                }
                case ("print") -> {
                    System.out.println();
                    commandPrint.realization(console);
                }
                case ("toggle") -> {
                    System.out.println();
                    commandToggle.realization(console);
                }
                case ("search") -> {
                    System.out.println();
                    commandSearch.realization(console);
                }
                case ("delete") -> {
                    System.out.println();
                    commandDelete.realization(console);
                }
                case ("edit") -> {
                    System.out.println();
                    commandEdit.realization(console);
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
