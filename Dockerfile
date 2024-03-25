FROM bellsoft/liberica-openjdk-alpine:21
LABEL authors="JAGO industries"
#CMD ["mvn spring-boot:build-image"]
#COPY /.mvn/maven-wrapper.jar maven-wrapper.jar
#COPY mvnw.cmd mvnw.cmd
COPY  target/*.jar sif-vju-backend.jar
COPY target/classes/application.yaml .
ENTRYPOINT ["java", "-jar", "/sif-vju-backend.jar"]