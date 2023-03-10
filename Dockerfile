# use jdk 19
FROM openjdk:19-jdk-slim

# enter into /app folder
WORKDIR /app

# install dependencies
RUN apt-get update && apt-get install -y git maven alsa-utils pulseaudio

# clone application and build executable
RUN git clone https://github.com/alessandro-somigli/java-timer-cli-project .
RUN mvn -B dependency:resolve
RUN mvn -B package

# enable files
RUN chmod 777 /app
RUN chmod +x entrypoint.sh

# add user
RUN useradd -ms /bin/bash user
RUN chown -R user:user /app
RUN chown user:user entrypoint.sh

USER user

ENTRYPOINT ["./entrypoint.sh"]
