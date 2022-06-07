package com.example.main.client;

import com.example.main.core.TaskMgmt;
import com.example.main.db.TaskMgmtDAO;
import com.example.main.resources.HelloResource;
import com.example.main.resources.TaskManagementResource;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(DropwizardExtensionsSupport.class)
public class CustomClientTest {

    private static final ResourceExtension EXT = ResourceExtension.builder()
            .addResource(new HelloResource())
            .build();

    @Test
    void shouldPing() {
        final String response = EXT.target("/hello")
                .request().get(String.class);
        assertEquals("Hello world!", response);
    }
}
