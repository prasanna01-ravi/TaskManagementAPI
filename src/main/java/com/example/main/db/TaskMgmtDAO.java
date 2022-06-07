package com.example.main.db;

import com.example.main.core.TaskCompletionViewModel;
import com.example.main.core.TaskMgmt;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class TaskMgmtDAO extends AbstractDAO<TaskMgmt> {

    public TaskMgmtDAO(SessionFactory factory) {
        super(factory);
    }

    public TaskMgmt findById(Long id) {
        return get(id);
    }

    public TaskMgmt create(TaskMgmt taskManagement) {
        return persist(taskManagement);
    }

    public TaskMgmt updateCompletedState(long id, TaskCompletionViewModel taskCompletionViewModel) {
        TaskMgmt taskManagement = get(id);
        taskManagement.setIsCompleted(taskCompletionViewModel.isCompleted());
        return persist(taskManagement);
    }

    public List<TaskMgmt> findAll() {
        return list(namedTypedQuery("com.example.main.core.TaskMgmt.findAll"));
    }
}
