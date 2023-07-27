FROM openjdk:11-jre-slim
WORKDIR /app
COPY ./ChallengePackage/demo/target/*.jar /app.jar
CMD [ "java", "-jar", "app.jar" ]
