FROM openjdk:11-jre-slim

WORKDIR /app

# Copy the JAR file into the image
COPY ./ChallengePackage/demo/target/*.jar /app/

# Add a label with the version of the application
LABEL app.version="1.0.0"

# Set environment variables (if needed)
ENV APP_NAME="My Java App"

# Run the application when the container starts
CMD ["java", "-jar", "app.jar"]

