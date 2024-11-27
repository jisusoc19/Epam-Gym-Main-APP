
FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/task_3-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} task_3.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/task_3.jar"]