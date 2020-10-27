
FROM openjdk:8-jdk-alpine
EXPOSE 9019
COPY /target/pcks-roadbrewa-category-core-0.0.1-SNAPSHOT.jar pcks-roadbrewa-category-core-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/pcks-roadbrewa-category-core-0.0.1-SNAPSHOT.jar"]