package com.example.main.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static io.dropwizard.jackson.Jackson.newObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

public class TaskMgmtTest {

    private static final ObjectMapper MAPPER = newObjectMapper();

    @Test
    void serializesToJSON() throws Exception {

        final TaskMgmt taskMgmt = new TaskMgmt(100001, "TestTempTask", false,
                (new SimpleDateFormat("dd/MM/yyyy").parse("05/06/2022")));
        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(getClass().getResource("/fixtures/task.json"), TaskMgmt.class));
        assertThat(MAPPER.writeValueAsString(taskMgmt)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final TaskMgmt taskMgmt = new TaskMgmt(100001, "TestTempTask", false,
                (new SimpleDateFormat("dd/MM/yyyy").parse("05/06/2022")));
        TaskMgmt taskMgmt1 = MAPPER.readValue(getClass().getResource("/fixtures/task.json"), TaskMgmt.class);
        assertThat(taskMgmt1)
                .isEqualTo(taskMgmt);
    }
}
