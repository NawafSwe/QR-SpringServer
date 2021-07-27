FROM openjdk:11
COPY  ./target/*.jar /app.jar
EXPOSE 6060
ENTRYPOINT ["java","-jar","/app.jar"]
