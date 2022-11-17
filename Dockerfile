FROM openjdk:11.0.11-jre-slim

EXPOSE 8080

# COPY ./build/libs/mwo4-0.0.1-SNAPSHOT.jar /usr/app/
# WORKDIR /usr/app
RUN mkdir /app

COPY build/libs/*.jar /app/mwo4.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/mwo4.jar"]

# ENTRYPOINT ["java", "-jar", "mwo4-0.0.1-SNAPSHOT.jar"]