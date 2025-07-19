# === Step 1: Build stage using Maven ===
FROM maven:3.9.6-eclipse-temurin-21 as build

WORKDIR /backend

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build
COPY . .
RUN mvn clean package -DskipTests

# === Step 2: Run stage using JRE only ===
FROM eclipse-temurin:21-jre

WORKDIR /backend

# Copy the built JAR from the builder stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080 (default Spring Boot port)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "anjuman_sayyed_mohalla-0.0.1-SNAPSHOT.jar"]
