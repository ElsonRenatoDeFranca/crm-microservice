FROM openjdk:8-jdk-alpine

VOLUME /tmp

COPY target/spring-boot-docker.jar hrm-demo-app.jar

ENTRYPOINT ["java", "-jar", "/hrm-demo-app.jar"]

EXPOSE 8080
