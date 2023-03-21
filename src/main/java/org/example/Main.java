package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Task task = new Task();
        int exit = 0;
        while (exit == 0) {
            System.out.print("1. add - добавление задачи \n" +
                    "2. print - печать списка невыолненых задач \n" +
                    "3. print all - печать списка всех задач \n" +
                    "3. toggle - изменение статуса задачи \n" +
                    "4. quit - завершение работы \n" +
                    "Введите одну из представленных команд:");
            Scanner console = new Scanner(System.in);
            String choice = console.nextLine();
            choice = choice.replaceAll("\\s+","");
            switch (choice) {
                case ("add"):
                    System.out.println();
                    System.out.print("Введите описание задачи: ");
                    String newTask = console.nextLine();
                    task.add(newTask);
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
                    System.out.println();
                    System.out.println("Возможно вы ввели не существующую комманду или опечатались. Попробуйте снова :)");
                    System.out.println();
                    break;

            }
        }
    }
}