FROM maven:3.8-openjdk-17-slim AS build
COPY ./pom.xml /app/
COPY ./src /app/src/
RUN mvn -f /app/pom.xml clean package

FROM openjdk:17-slim-buster
COPY --from=build /app/target/wiki-server.jar /
ENTRYPOINT ["java", "-Dspring.profiles.active=remote", "-jar", "wiki-server.jar"]