FROM maven:3.9.0-eclipse-temurin-17 AS build
COPY /src/main/java/training/cloud/crmmicroservice /app/crm
COPY /pom.xml /app/crm
RUN /pom.xml /app/crm
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip

FROM sensedia/openjdk17-base:latest
VOLUME /tmp
COPY --from=build /target/crm-microservice-docker.jar crm-microservice.jar
ENTRYPOINT ["java", "-jar", "/crm-microservice.jar"]
EXPOSE 8080
