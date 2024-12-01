#Use the official OpenJDK 17 image from Docker Hub
FROM openjdk:17-jdk-slim

#Set the working directory in the container
WORKDIR /app

#Copy your compiled Java application
COPY target/crm-microservice-docker.jar /app/crm-microservice-docker.jar

#Comand to tun the application
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","crm-microservice-docker.jar"]