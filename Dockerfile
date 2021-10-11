
FROM openjdk:8-jdk-alpine
EXPOSE 9022
COPY /target/pcks-roadway-vehicle-core-0.0.1-SNAPSHOT.jar pcks-roadway-vehicle-core-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/pcks-roadway-vehicle-core-0.0.1-SNAPSHOT.jar"]