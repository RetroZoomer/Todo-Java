package org.example.userCommand.interfaceCheck;

import org.example.entity.Task;

import java.util.Map;

public interface HasTask {
    boolean hasTask(int id, Map<Integer, Task> tasks);
}
