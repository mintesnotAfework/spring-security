FROM openjdk:24-oraclelinux9


RUN mkdir -p /app/spring_security
WORKDIR /app/spring_security

ARG JAR_FILE=target/*.jar 
COPY ./target/security-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java","-jar","/app/spring_security/app.jar" ]