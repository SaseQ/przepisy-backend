FROM openjdk:17 AS build
ADD target/przepisy-backend-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "przepisy-backend-0.0.1-SNAPSHOT.jar"]
