package com.example.main.db;

import com.example.main.core.TaskMgmt;
import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
public class DatabaseTest {

    public DAOTestExtension database = DAOTestExtension.newBuilder().addEntityClass(TaskMgmt.class).build();

    private TaskMgmtDAO DAO;

    @BeforeEach
    public void setUp() {
        DAO = new TaskMgmtDAO(database.getSessionFactory());
    }

    @Test
    public void createTask() throws ParseException {
        TaskMgmt taskMgmt = new TaskMgmt( "TestTempTask", false,
                (new SimpleDateFormat("dd/MM/yyyy").parse("05/06/2022")));

        TaskMgmt saveTaskMgmt = database.inTransaction(() -> {
            return DAO.create(taskMgmt);
        });
        assertThat(saveTaskMgmt.getId()).isGreaterThan(0);
        assertThat(saveTaskMgmt.getTaskName()).isEqualTo("TestTempTask");
        assertThat(saveTaskMgmt.isCompleted()).isEqualTo(false);
        assertThat(saveTaskMgmt.getDueDate())
                .isEqualTo(new SimpleDateFormat("dd/MM/yyyy").parse("05/06/2022"));
    }
}
