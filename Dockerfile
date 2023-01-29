FROM openjdk:19-jdk-slim

WORKDIR /app

RUN apt-get update && apt-get install -y git maven

RUN git clone https://github.com/alessandro-somigli/java-timer-cli-project .

RUN mvn -B dependency:resolve

RUN mvn -B package

CMD java -jar target/timer-cli-project-1.0-SNAPSHOT.jar "$@"
