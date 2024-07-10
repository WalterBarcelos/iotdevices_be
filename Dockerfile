# Use the official Gradle image with JDK 17 to build the app
FROM gradle:7.4.2-jdk17 AS build
WORKDIR /home/app
COPY gradle.properties ./
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle settings.gradle ./
COPY src ./src

# Install curl command
RUN apt-get update && apt-get install -y curl

# Verify internet connectivity and print Java version for debugging
RUN wget -q --spider http://google.com && echo "Internet is accessible" && java -version

# Build the application with detailed logging
RUN ./gradlew build --no-daemon --info

# Use the official OpenJDK image with JDK 17 to run the app
FROM openjdk:17-jdk-slim
COPY --from=build /home/app/build/libs/*.jar /usr/local/lib/iotdeviceapp.jar
ENTRYPOINT ["java", "-jar", "/usr/local/lib/iotdeviceapp.jar"]
