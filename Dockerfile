FROM openjdk:19-jdk-slim

RUN useradd -ms /bin/bash admin

WORKDIR /app

RUN apt-get update && apt-get install -y git maven alsa-utils pulseaudio

RUN git clone https://github.com/alessandro-somigli/java-timer-cli-project .
RUN mvn -B dependency:resolve
RUN mvn -B package

RUN chown -R admin:admin /app
RUN chmod 755 /app

USER admin

# CMD pulseaudio --start && java -jar target/timer-cli-project-1.0-SNAPSHOT.jar "$@"
ENTRYPOINT["/entrypoint.sh"]
