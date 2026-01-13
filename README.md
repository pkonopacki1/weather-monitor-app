# Weather Monitor App

Application monitoring weather in a given city.

> IMPORTANT
> This is a test application created to help in the process of *Kubernetes* learning.

## Overview

![Containers diagram](diagram.drawio.png)

## Running the application in kubernetes

Prerequisites:

- docker
- minikube
- kubernetes and helm
- mvn
- api key for [weather api](https://www.weatherapi.com/my/)

1. Set WEATHER_API_KEY in the `kubernetes/deployment-producer.yaml`
2. Run the script:
   `./run.sh`

This will create deployments: producer and consumer. Use chosen tool (k9s or kctl) to see the consumer logs to confirm
that the messages are being consumed.

## Running locally (e.g. for debugging)

1. Create kafka broker:
   `docker compose up -d broker`
2. Create topic:

  ```shell
  docker exec -it broker /opt/kafka/bin/kafka-topics.sh \
--create \
--topic weather.report \
--bootstrap-server broker:9092 \
--partitions 3
  ```

3. Run the applications
