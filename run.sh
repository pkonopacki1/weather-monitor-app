#! /bin/bash

cd weather-monitor-app-consumer
mvn clean package
cd ../weather-monitor-app-producer
mvn clean package
cd ../docker
docker compose up -d broker

docker exec -it broker /opt/kafka/bin/kafka-topics.sh \
  --create \
  --topic weather.report \
  --bootstrap-server broker:9092 \
  --partitions 3

docker compose up -d producer consumer
