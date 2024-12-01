# Use the official Gradle image to build the project
FROM gradle:8.10.2-jdk17 AS build

# Copy the project files to the container
COPY --chown=gradle:gradle . /home/gradle/project

# Set the working directory
WORKDIR /home/gradle/project

# Build the project using Gradle
RUN gradle build --no-daemon

# Use the official OpenJDK image for the runtime
FROM openjdk:17-jdk-slim

# Copy the built jar file to the container
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]

