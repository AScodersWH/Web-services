#!/bin/bash

./gradlew clean bootRepackage

docker build --rm . --tag as895052253/zuul-service:${VER:?invalid version}
docker push as895052253/zuul-service:${VER:?invalid version}

export VER
docker stack deploy todo -c docker-compose.yml