package org.example.userCommand;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Task;
import org.example.userCommand.interfaceCommand.ITaskDAO;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Data
public class TaskDAO implements ITaskDAO {
    protected int id;
    protected static Map<Integer, Task> tasks = new LinkedHashMap<>();

    protected Helper helper;
    protected Check check;

    public TaskDAO(Helper helper, Check check) {
        this.helper = helper;
        this.check = check;
    }

    public void save(String line) {
        id++;
        tasks.put(id, new Task(line));
        log.debug("{}", line);
    }

    @Override
    public void delete(int id) {
        tasks.remove(id);
        log.debug("{}", id);
    }

}
