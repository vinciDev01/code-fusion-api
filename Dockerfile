# Use Maven to build application
FROM maven:3.9.8-amazoncorretto-17-al2023 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
RUN ls -l /app/target  

# Use OpenJDK to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/code-fusion-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "app.jar" ]