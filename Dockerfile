FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

COPY target/*.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]
