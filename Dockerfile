# Stage 1: Build dùng Java 21 (Bản này có sẵn trên Docker Hub và cực ổn định)
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Bước 1: Cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Bước 2: Build code
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime dùng Java 26 (Dùng bản của Oracle vì nó cập nhật sớm nhất cho Java 26)
# Nếu bản 26 vẫn báo lỗi, hãy lùi về 21 là chắc chắn nhất
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]