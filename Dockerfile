# -------- STAGE 1: Build --------
    FROM eclipse-temurin:17-jdk-alpine as builder

    WORKDIR /app
    
    COPY .mvn/ .mvn/
    COPY mvnw pom.xml ./
    RUN chmod +x mvnw && ./mvnw dependency:go-offline
    
    COPY src ./src
    RUN ./mvnw clean package -DskipTests
    
    # -------- STAGE 2: Runtime --------
    FROM eclipse-temurin:17-jdk-alpine
    
    WORKDIR /app
    
    # Copy only the final   Jar
    COPY --from=builder /app/target/portal-0.0.1-SNAPSHOT.jar .
    
    EXPOSE 8080
    
    CMD ["java", "-jar", "portal-0.0.1-SNAPSHOT.jar"]
    