
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app


COPY pom.xml .
COPY .mvn .mvn

COPY src src

RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre-jammy


WORKDIR /app

COPY --from=build /app/target/Agro_Mapping-0.0.1-SNAPSHOT.jar .

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "Agro_Mapping-0.0.1-SNAPSHOT.jar"]