FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/bioprac-0.0.1-SNAPSHOT.war
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]