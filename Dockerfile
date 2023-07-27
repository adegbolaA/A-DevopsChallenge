FROM openjdk:11-jdk-alpine
WORKDIR /app
COPY ./ChallengePackage/demo/target/*.jar /app.jar
CMD [ "java", "-jar", "app.jar" ]
