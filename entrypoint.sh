#!/bin/bash
pulseaudio --start && java -jar target/timer-cli-project-1.0-SNAPSHOT.jar "$@"
