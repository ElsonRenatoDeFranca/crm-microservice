########build stage########
#FROM maven:3.8.3-openjdk-17 as buildstage
#COPY pom.xml .
#RUN mvn -B -f pom.xml dependency:go-offline

# Copy all other project files and build project
#COPY . .
#RUN mvn -B clean install -DskipTests=true

##Copy the generated jar file into container directory
FROM sensedia/openjdk17-base:latest
WORKDIR /app
# COPY --from=buildstage ./target/crm-microservice-docker.jar ./
COPY target/crm-microservice-docker.jar /app/crm-microservice-docker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","crm-microservice-docker.jar"]