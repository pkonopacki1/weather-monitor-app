# Weather Monitor App

Application monitoring weather in a given city.

> IMPORTANT
> This is a test application created to help in the process of *Kubernetes* learning.

## Overview

![Containers diagram](diagram.drawio.png)

## Running the application in docker containers

Prerequisites:
- docker
- mvn

1. Export weather api key
   `export WEATHER_API_KEY=<API_KEY>`
2. Run the script:
   `./run.sh`

This will create containers: broker, producer and consumer.
