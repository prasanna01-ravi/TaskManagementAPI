package com.example.main.resources;

import com.example.main.core.TaskCompletionViewModel;
import com.example.main.core.TaskMgmt;
import com.example.main.db.TaskMgmtDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
@Api(value="/taskmgmt")
@Path("/taskmgmt")
@Produces(MediaType.APPLICATION_JSON)
public class TaskManagementResource {

    private final TaskMgmtDAO taskMgmtDAO;

    public TaskManagementResource(TaskMgmtDAO taskMgmtDAO) {
        this.taskMgmtDAO = taskMgmtDAO;
    }

    @POST
    @UnitOfWork
    public TaskMgmt createTask(@Valid TaskMgmt taskManagement) {
        return taskMgmtDAO.create(taskManagement);
    }

    @PUT
    @Path("/{taskMgmtId}")
    @UnitOfWork
    public TaskMgmt updateCompletionState(@PathParam("taskMgmtId") long taskMgmtId,
                                          @Valid TaskCompletionViewModel taskCompletionViewModel) {
        return taskMgmtDAO.updateCompletedState(taskMgmtId, taskCompletionViewModel);
    }

    @GET
    @UnitOfWork
    public List<TaskMgmt> listTasks() {
        return taskMgmtDAO.findAll();
    }
}
