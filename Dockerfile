FROM openjdk:17-jdk-alpine
LABEL authors="JAGO industries"
#CMD ["mvn spring-boot:build-image"]
#COPY /.mvn/maven-wrapper.jar maven-wrapper.jar
#COPY mvnw.cmd mvnw.cmd
COPY  target/*.jar sif-vju-backend.jar
ENTRYPOINT ["java", "-jar", "/sif-vju-backend.jar"]