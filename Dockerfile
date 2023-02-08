# Use an existing PostgreSQL image as the base image
FROM postgres:12

# Set the environment variables for the database
ENV POSTGRES_USER myuser
ENV POSTGRES_PASSWORD mypassword

# Add the database creation script to the Docker image
COPY create_drones_database.sql /docker-entrypoint-initdb.d/

# Use an official Maven image as the base image
FROM maven:3.6-jdk-11 as build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file to the image
COPY pom.xml .

# Download all dependencies
RUN mvn dependency:go-offline

# Copy the application source code to the container
COPY . .

# Build the Spring Boot application
RUN mvn clean install

# Create a new image from the openjdk:11-jdk-slim image
FROM openjdk:11-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built Jar file from the build image
COPY --from=build /app/target/*.jar app.jar

# Set the environment variable for the JAVA_OPTS
ENV JAVA_OPTS=""

# Run the application
CMD ["java", "-jar", "app.jar"]
