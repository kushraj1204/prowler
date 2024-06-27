# Dockerfile
FROM openjdk:21
VOLUME /tmp
COPY target/prowler-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

