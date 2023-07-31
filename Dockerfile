FROM openjdk:11-jre-slim
WORKDIR /app
COPY ./ChallengePackage/demo/target/*.jar /app.jar
RUN chmod 755 /app.jar
CMD [ "java", "-jar", "app.jar" ]
