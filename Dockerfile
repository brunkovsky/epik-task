FROM adoptopenjdk/openjdk11
COPY ./target/epik-task-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]