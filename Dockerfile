FROM gradle:7.6.0-jdk17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper and project files
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle.kts settings.gradle.kts ./

# Download dependencies
RUN ./gradlew dependencies --no-daemon

# Copy the source code
COPY src ./src

# Build the application
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim

# Install Nginx
RUN apt-get update && apt-get install -y nginx && rm -rf /var/lib/apt/lists/*

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Copy Nginx configuration
COPY nginx.conf /etc/nginx/nginx.conf

# Expose ports (Spring Boot and Nginx)
EXPOSE 8080 80

# Start Nginx and Spring Boot
CMD ["sh", "-c", "nginx && java -jar /app/app.jar"]
