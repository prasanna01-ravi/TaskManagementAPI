package com.example.main;

import com.example.main.db.TaskMgmtDAO;
import com.example.main.resources.HelloResource;
import com.example.main.core.TaskMgmt;
import com.example.main.resources.TaskManagementResource;
import io.dropwizard.Application;

import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class TestAPIApplication extends Application<TestAPIConfiguration> {

    private final HibernateBundle<TestAPIConfiguration> hibernate = new HibernateBundle<TestAPIConfiguration>(TaskMgmt.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(TestAPIConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new TestAPIApplication().run(args);
    }

    @Override
    public String getName() {
        return "TaskManagement";
    }

    @Override
    public void initialize(final Bootstrap<TestAPIConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addBundle(new MigrationsBundle<TestAPIConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(TestAPIConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

        bootstrap.addBundle(new SwaggerBundle<TestAPIConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(TestAPIConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });

        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final TestAPIConfiguration configuration,
                    final Environment environment) {

        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        final TaskMgmtDAO taskMgmtDAO = new TaskMgmtDAO(hibernate.getSessionFactory());

        //environment.healthChecks().register("database", new DatabaseHealthCheck(database));
        environment.jersey().register(new HelloResource());
        environment.jersey().register(new TaskManagementResource(taskMgmtDAO));
    }

}
