FROM maven:3.6-jdk-8-alpine as MAVEN_BUILD

WORKDIR /build

COPY pom.xml .

RUN mvn clean dependency:go-offline

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:8-jre-alpine

WORKDIR /app

EXPOSE 9090

ENV DB_URL localhost:5432/library
ENV DB_USER postgres
ENV DB_PASSWORD 112233

COPY --from=MAVEN_BUILD /build/target/library*.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]
