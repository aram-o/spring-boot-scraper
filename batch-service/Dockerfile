FROM openjdk:11-jdk-slim
COPY target/batch-service.jar /opt/batch-service.jar

ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "-jar", "/opt/batch-service.jar"]
EXPOSE 8080