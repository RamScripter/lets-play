FROM maven:3.9.6-eclipse-temurin-21 as maven-builder
WORKDIR /app
COPY . /app
RUN mvn clean install

FROM eclipse-temurin:21-jdk 
WORKDIR /app
EXPOSE 8080
COPY target/*.jar app.jar
COPY src/main/resources/application.properties-docker /app/application.properties
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "app.jar"]
