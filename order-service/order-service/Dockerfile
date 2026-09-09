# Step 1: Base Build Layer System Engine Configuration Setup
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Core runtime settings compilation layer verification dependencies pass
COPY pom.xml .
COPY src ./src

# Fast clean framework production components execution logic
RUN mvn clean package -DskipTests

# Step 2: Lightweight Production Secure Alpine Runtime Environment Module
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# builder environment system layers module compile path reference binary input variable logic fetch parameter layer target mapping
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
