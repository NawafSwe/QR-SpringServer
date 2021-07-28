#build packages
FROM maven:3.6.3-jdk-11-slim AS build
COPY src /usr/app/src
COPY pom.xml /usr/app
RUN mvn clean install -DskipTests -f /usr/app/pom.xml

#build app
FROM openjdk:11
COPY --from=build usr/app/target/.*jar  /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
