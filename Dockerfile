# syntax=docker/dockerfile:1
FROM openjdk:11
WORKDIR /usr/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY . .
CMD ["./mvnw", "spring-boot:run"]
