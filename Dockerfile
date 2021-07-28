#build packages
FROM maven:3.6.3-jdk-11-slim AS build

WORKDIR /usr/app/
COPY pom.xml  /usr/app
COPY src /usr/app/src
RUN --mount=type=cache,target=/root/.m2 mvn clean package  -Dmaven.test.skip

#build app
FROM openjdk:11
WORKDIR /app
COPY --from=build usr/app/target/.*jar  /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
