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
- kubernetes and helm (I use [Strimzi](https://strimzi.io/) for the Kafka cluster)
- mvn
- api key for [weather api](https://www.weatherapi.com/my/)

1. Export WEATHER_API_KEY environemnt variable, it will be used to create a secret in Kubernetes.
2. Run the script:
   `./run.sh`

This will create deployments: producer and consumer. Use chosen tool (k9s or kctl), select 'weather' namespace and see
the consumer logs to confirm that the messages are being consumed.

## Cleanup

Run `./cleaups.sh` to clean up the resources.
