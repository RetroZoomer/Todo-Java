package org.example.userCommand;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Helper {
    private boolean helpPrinted = false;

    public void help() {
        if (helpPrinted) {
            return;
        }
        log.error("help printed");
        System.out.println("""
                Возможные команды:\s
                \t add <описание задачи>\s
                \t print [all]
                \t toggle <идентификатор задачи>
                \t search <substring>
                \t delete <идентификатор задачи>
                \t edit <идентификатор задачи> <новое значение>
                \t quit""");
        helpPrinted = true;
    }

    public void wrongArgument() {
        log.error("wrong argument was entered");
        System.err.println("Недопустимый аргумент команды");
        help();
    }
}
