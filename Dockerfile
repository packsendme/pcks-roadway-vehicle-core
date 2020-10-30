
FROM openjdk:8-jdk-alpine
EXPOSE 9022
COPY /target/pcks-roadbrewa-vehicle-core-0.0.1-SNAPSHOT.jar pcks-roadbrewa-vehicle-core-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/pcks-roadbrewa-vehicle-core-0.0.1-SNAPSHOT.jar"]