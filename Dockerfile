FROM openjdk:11
VOLUME /tmp
COPY  ./target/*.jar /app.jar
EXPOSE 6060
ENTRYPOINT ["java","-jar","/app.jar"]
