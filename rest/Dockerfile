# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="val@filin.us"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/rest-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} rest.jar

ADD ./wait-for.sh wait-for.sh
RUN chmod +x /wait-for.sh

HEALTHCHECK --interval=10s --timeout=3s --retries=10 \
  CMD curl --silent --fail localhost:8080/actuator/health || exit 1

# Run the jar file
ENTRYPOINT echo "entrypoint"; /wait-for.sh -t 60 docker-mysql:3306 -- java -Djava.security.egd=file:/dev/./urandom -jar /rest.jar