FROM openjdk:11.0.11-jre-slim

EXPOSE 8080

COPY ./build/libs/mwo4-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "mwo4-0.0.1-SNAPSHOT.jar"]