FROM sensedia/openjdk17-base:latest
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/crm-microservice-docker.jar
ADD ${JAR_FILE} crm-microservice-docker.jar
ENTRYPOINT ["java","-jar","crm-microservice-docker.jar"]