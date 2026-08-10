# ---- Build stage ----
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /build

# Cache dependency resolution separately from source changes
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -q -B dependency:go-offline

COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -q -B clean package -DskipTests

# ---- Runtime stage ----
FROM eclipse-temurin:17-jre AS runtime
WORKDIR /app

RUN useradd --create-home --shell /bin/false appuser
COPY --from=build /build/target/rr-accountancy-app.jar app.jar
RUN chown appuser:appuser app.jar
USER appuser

EXPOSE 7000

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
