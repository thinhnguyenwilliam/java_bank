# Stage 1: Build
# Start with a Maven image that includes JDK 21
FROM maven:3.9.8-amazoncorretto-21 AS build

# Set the working directory
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml ./
COPY src ./src

# Build the project
RUN mvn package -DskipTests

# Stage 2: Runtime
# Use Amazon Corretto JDK 21 for the runtime environment
FROM amazoncorretto:21.0.4

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
