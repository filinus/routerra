# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="val@filin.us"

# Add a volume pointing to /tmp
VOLUME /tmp

# The application's jar file
ARG JAR_FILE=target/aggregator-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} aggregator.jar

ADD ./wait-for.sh wait-for.sh
RUN chmod +x /wait-for.sh

# Run the jar file
ENTRYPOINT echo "wait for mysql"; /wait-for.sh -t 60 docker-mysql:3306 -- java -Djava.security.egd=file:/dev/./urandom -jar /aggregator.jar