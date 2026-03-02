FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x gradlew

COPY src src

RUN ./gradlew bootWar -x test --no-daemon

From eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.war app.war

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.war"]