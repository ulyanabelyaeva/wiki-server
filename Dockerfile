FROM maven:3.8-openjdk-17-slim AS build
COPY ./pom.xml /app/
COPY ./src /app/
RUN mvn -f /app/pom.xml clean package

FROM openjdk:17-slim-buster
COPY --from=build /app/target/wiki-server-1.0-SNAPSHOT.jar /
ENTRYPOINT ["java", "-jar", "wiki-server-1.0-SNAPSHOT.jar"]