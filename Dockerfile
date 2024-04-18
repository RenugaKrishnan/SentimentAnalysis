# Base image with Java runtime
FROM adoptopenjdk/openjdk11:ubi AS build

# Set working directory
WORKDIR /app

# Install dependencies using Gradle wrapper (adjust if using a different approach)
RUN ./gradlew clean build -DskipTests=true

# Copy JAR file from build stage
COPY build/libs/*.jar /app/app.jar

# Expose container port (replace 8080 with your actual port)
EXPOSE 8090

# Start the Spring Boot application
CMD ["java", "-jar", "app.jar"]
