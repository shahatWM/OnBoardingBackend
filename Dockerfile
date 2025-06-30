FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy Maven wrapper and build files
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Copy application source code
COPY src ./src

# Build the application and copy output
RUN ./mvnw clean package -DskipTests

# Expose port (Spring Boot default)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app/target/portal-0.0.1-SNAPSHOT.jar"]
