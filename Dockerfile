FROM sensedia/openjdk17-base:latest
WORKDIR /app
COPY target/crm-microservice-docker.jar /app/crm-microservice-docker.jar
ENTRYPOINT ["java","-jar","crm-microservice-docker.jar"]