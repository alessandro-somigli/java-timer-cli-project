FROM openjdk:19-jdk-slim

WORKDIR /app

RUN apt-get update && apt-get install -y git maven tree

RUN git clone https://github.com/alessandro-somigli/java-maven-chat-project.git .

RUN mvn -B dependency:resolve

RUN mvn -B package

ENTRYPOINT ["tree"]
# ENTRYPOINT ["java", "-jar", "target/chat-1.0-SNAPSHOT.jar", "-t", "30"]
