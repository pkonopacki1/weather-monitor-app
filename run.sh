#! /bin/bash

cd weather-monitor-app-consumer
mvn clean package
cd ../weather-monitor-app-producer
mvn clean package
cd ../docker
docker compose build
minikube start --nodes=3
echo "Loading producer image to minikube..."
minikube image load weather-app-producer
echo "✅ Done!"
echo "Loading consumer image to minikube..."
minikube image load weather-app-consumer
echo "✅ Done!"
docker compose up -d broker
cd ../kubernetes
kubectl apply -f deployment-producer.yaml
kubectl apply -f deployment-consumer.yaml
