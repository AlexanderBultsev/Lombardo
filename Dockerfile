# Этап сборки
FROM gradle:8.7 AS build
WORKDIR /home/gradle/app
COPY . .
RUN gradle build --no-daemon

# Этап запуска
FROM openjdk:21
EXPOSE 8080
WORKDIR /app
COPY --from=build /home/gradle/app/build/libs/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]