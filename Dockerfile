
FROM openjdk:8-jdk-alpine
EXPOSE 9019
COPY /target/pcks-roadway-category-core-0.0.1-SNAPSHOT.jar pcks-roadway-category-core-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/pcks-roadway-category-core-0.0.1-SNAPSHOT.jar"]