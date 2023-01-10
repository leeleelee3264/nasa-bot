FROM openjdk:11-jdk
RUN apt-get update && apt-get -y install sudo
ARG JAR_FILE="build/libs/today-earth-0.0.1-SNAPSHOT.jar"
COPY ${JAR_FILE} app.jar
ENV    PROFILE prod
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar","/app.jar"]