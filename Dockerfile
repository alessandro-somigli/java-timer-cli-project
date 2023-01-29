FROM openjdk:19-jdk-slim

WORKDIR /app

RUN apt-get update && apt-get install -y git maven

RUN git clone https://github.com/alessandro-somigli/java-maven-chat-project.git .

RUN mvn -B dependency:resolve

RUN mvn -B package

ENTRYPOINT ["java", "-jar", "timer-cli-project.jar", "-t", "30"]
