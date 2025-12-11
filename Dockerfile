FROM clojure:temurin-21-tools-deps-noble AS builder

WORKDIR /app

# Copy project files and download dependencies (for better caching)
COPY deps.edn build.clj ./
RUN mkdir -p src resources config
RUN clojure -P -T:build

# Copy the rest of the source code
COPY src ./src
# COPY resources ./resources
COPY config ./config

# Build the uberjar
RUN clojure -T:build uber

# --
# Final image
FROM openjdk:21-jdk
WORKDIR /app

# Create a non-root user and group
RUN groupadd -r appuser && useradd -r -g appuser appuser

# Copy the built uberjar
COPY --from=builder /app/target/basic-microservice-example-0.0.1-SNAPSHOT-standalone.jar app.jar

# Set ownership and permissions
RUN chown -R appuser:appuser /app && chmod 755 /app/app.jar

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
