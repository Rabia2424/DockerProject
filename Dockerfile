FROM openjdk:17
WORKDIR /app
COPY build/libs/DockerProject-0.0.1-SNAPSHOT.jar DockerProject-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","DockerProject-0.0.1-SNAPSHOT.jar"]