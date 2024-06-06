FROM maven:3.8.8-eclipse-temurin-21-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from =build /target/crm_for_learning_center-0.0.1-SNAPSHOT.jar crm_for_learning_center.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","crm_for_learning_center.jar"]