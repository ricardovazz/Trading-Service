# Use an official lightweight Java runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file into the container
COPY target/settlementservice-0.0.1-SNAPSHOT.jar /app/my-service.jar

# Make port 8080 available outside this container
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java","-jar","/app/my-service.jar"]
