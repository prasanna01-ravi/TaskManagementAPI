FROM openjdk:18-oracle

WORKDIR /var/dropwizard-rest-stub

COPY target/TestAPI-1.0-SNAPSHOT.jar /var/dropwizard-rest-stub/TestAPI-1.0-SNAPSHOT.jar

COPY docker_config.yml /var/dropwizard-rest-stub/config.yml

COPY init_config.yml /var/dropwizard-rest-stub/init_config.yml

RUN cat /var/dropwizard-rest-stub/config.yml

RUN java -jar TestAPI-1.0-SNAPSHOT.jar check init_config.yml

ENTRYPOINT ["java", "-jar", "TestAPI-1.0-SNAPSHOT.jar", "server", "config.yml"]

