FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

RUN apt-get update && apt-get install -y git maven

RUN git clone https://github.com/alessandro-somigli/java-maven-chat-project.git .

RUN mvn -B dependency:resolve

RUN mvn -B package

ENTRYPOINT ["java", "-jar", "target/timer-cli-project.jar", "-t", "30"]
