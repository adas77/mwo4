FROM openjdk:11.0.11-jre-slim

EXPOSE 8080

COPY ./build/libs/my-app-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "my-app-0.0.1-SNAPSHOT.jar"]