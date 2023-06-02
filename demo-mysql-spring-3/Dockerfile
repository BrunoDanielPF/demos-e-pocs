FROM openjdk:17-oracle
MAINTAINER Bruno daniel <frandalozzo@gmail.com>
ADD target/*.jar demo-mysql-spring-3-0.0.1-SNAPSHOT.jar.jar
COPY target/*.jar /app/demo-mysql-spring-3-0.0.1-SNAPSHOT.jar.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "/demo-mysql-spring-3-0.0.1-SNAPSHOT.jar.jar"]