FROM openjdk:11
VOLUME /tmp
EXPOSE 6060
COPY ./target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
