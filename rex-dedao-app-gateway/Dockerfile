# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
MAINTAINER Rex <wcl_xg0204@163.com>

# The application's jar file
ARG JAR_FILE=target/rex-dedao-app-gateway-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} rex-dedao-app-gateway.jar

# Run the jar file. Won't be override
ENTRYPOINT ["java","-jar","/rex-dedao-app-gateway.jar"]