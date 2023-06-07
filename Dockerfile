FROM gradle:jdk17 AS builder
WORKDIR /home/quote-collection-backend
COPY . .
RUN gradle build

FROM openjdk:17-jdk
WORKDIR /home/quote-collection-backend
COPY --from=builder /home/quote-collection-backend/build/libs/quote-collection-backend-0.0.1-SNAPSHOT.jar quote-collection-backend.jar
ENTRYPOINT ["java", "-jar", "quote-collection-backend.jar"]
