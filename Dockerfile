# Use image: Linux and Java
FROM openjdk:17-alpine

# Copy application to container
COPY target/app.jar /app.jar

# start application
ENTRYPOINT ["java","-jar","/app.jar"]
