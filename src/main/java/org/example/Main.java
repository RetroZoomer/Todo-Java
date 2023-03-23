package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Task task = new Task();
        int exit = 0;
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
                    task.print();
                    System.out.println();
                    break;
                case ("print all"):
                    // вывод всех задач
                    System.out.println();
                    System.out.println("Пока что просто заглушка");
                    System.out.println();
                    break;
                case ("toggle"):
                    System.out.println();
                    System.out.print("Введите идентификатор задачи: ");
                    String newToggle = console.nextLine();
                    task.toggle(Integer.parseInt(newToggle));
                    if (Integer.parseInt(newToggle) == task.getId() && task.getDescription() != null ){
                        System.out.println("Статус задачи изменен");
                    } else {
                        System.out.println("Такой задачи нет в списке");
                    }
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
        System.out.println("Возможные команды: \n" +
                "\tadd <описание задачи> \n" +
                "\tprint [all]\n" +
                "\ttoggle <идентификатор задачи>\n" +
                "\tquit");
    }
}