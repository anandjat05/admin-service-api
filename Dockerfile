FROM openjdk:8-jdk-alpine

MAINTAINER "anandjat08@gmail.com"

EXPOSE 8080

WORKDIR /usr/local/bin

COPY ./target/admin-service-0.0.1-SNAPSHOT.jar .

CMD ["java","-jar","admin-service-0.0.1-SNAPSHOT.jar"]