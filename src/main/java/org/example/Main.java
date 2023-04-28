package org.example;

import java.util.Scanner;

public class Main {

    static boolean helpPrinted = false;
    static int exit = 0;
    public static void main(String[] args) {
        Task task = new Task();
        Scanner console = new Scanner(System.in);
        while (exit != 1) {

            String choice = console.next();
            choice = choice.replaceAll("\\s+","");
            switch (choice) {
                case ("add"):
                    System.out.println();
                    task.add(console);
                    System.out.println();
                    break;
                case ("print"):
                    System.out.println();
                    task.print(console);
                    System.out.println();
                    break;
                case ("toggle"):
                    System.out.println();
                    task.toggle(console);
                    System.out.println();
                    break;
                case ("quit"):
                    exit = 1;
                    System.out.println("Завершение работы!");
                    break;
                default:
                    System.err.println("Возможно вы ввели не существующую комманду или опечатались. Попробуйте снова :)");
                    help();
                    break;
            }

        }

    }
    public static void help() {
        if (helpPrinted) {
            return;
        }
        System.out.println("Возможные команды: \n" +
                "\tadd <описание задачи> \n" +
                "\tprint [all]\n" +
                "\ttoggle <идентификатор задачи>\n" +
                "\tquit");
        helpPrinted = true;
    }
}