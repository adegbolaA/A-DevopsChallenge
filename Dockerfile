# Dockerfile
FROM openjdk:22-ea-26

WORKDIR /app

# Copying the JAR file into the image
COPY ./ChallengePackage/devops-project/target/devops-project-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8081

# Run the application 
CMD ["java", "-jar", "devops-project-0.0.1-SNAPSHOT.jar"]
