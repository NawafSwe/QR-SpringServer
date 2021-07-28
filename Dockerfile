#build packages
FROM maven:3.6.3-jdk-11-slim AS build
WORKDIR /app
COPY pom.xml  /app
COPY src /app/src
RUN --mount=type=cache,target=/root/.m2 mvn clean package  -Dmaven.test.skip

#build app
FROM openjdk:11
WORKDIR /app
COPY --from=build /app/target/.*jar  /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
