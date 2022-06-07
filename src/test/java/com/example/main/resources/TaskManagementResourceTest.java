package com.example.main.resources;

import com.example.main.core.TaskMgmt;
import com.example.main.db.TaskMgmtDAO;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(DropwizardExtensionsSupport.class)
public class TaskManagementResourceTest {
    private static final TaskMgmtDAO DAO = mock(TaskMgmtDAO.class);
    private static final ResourceExtension EXT = ResourceExtension.builder()
            .addResource(new TaskManagementResource(DAO))
            .build();
    private final ArgumentCaptor<TaskMgmt> taskCaptor = ArgumentCaptor.forClass(TaskMgmt.class);

    @BeforeEach
    void setUp(){
    }

    @AfterEach
    void tearDown() {
        reset(DAO);
    }

    @Test
    void getAllTasks() {
        final List<TaskMgmt> tasks = new ArrayList<TaskMgmt>();
        when(DAO.findAll()).thenReturn(tasks);
        final List<TaskMgmt> response = EXT.target("/taskmgmt")
                .request().get(tasks.getClass());
        assertThat(response).containsAll(tasks);
        verify(DAO).findAll();
    }
}
