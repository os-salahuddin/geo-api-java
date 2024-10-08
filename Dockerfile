# Use the official Maven image to build the application
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the application source
COPY src ./src

# Package the application
RUN mvn package -DskipTests

# Use a smaller base image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the packaged JAR file
COPY --from=build /app/target/geoip-demo-1.0-SNAPSHOT.jar ./geoip-demo.jar

# Run the application
CMD ["java", "-jar", "geoip-demo.jar"]