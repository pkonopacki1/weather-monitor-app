#! /bin/bash

echo "📝 Script starts..."
echo "📝 Package consumer application..."
cd weather-monitor-app-consumer
mvn clean package -q -DskipTests
echo "✅ Done!"
echo "📝 Package producer application..."
cd ../weather-producer-app
mvn clean package -q -DskipTests
echo "✅ Done!"
cd ../docker
echo "📝 Building docker images..."
docker compose build
echo "✅ Done!"
echo "📝 Strarting minikube cluster..."
minikube start --nodes=1
minikube image load weather-producer-app
echo "✅ Done!"
echo "📝Loading consumer image to minikube..."
minikube image load weather-app-consumer
echo "✅ Done!"
echo "📝Installing kafka server on the kubernetes cluster..."
kubectl create namespace kafka
helm install strimzi-cluster-operator oci://quay.io/strimzi-helm/strimzi-kafka-operator -n kafka
cd ../kubernetes
kubectl apply -f kafka-single-node.yaml
echo "✅ Kafka cluster installed!"
kubectl create namespace weather
kubectl create secret generic weather-secrets --from-literal=weather_api_key="$WEATHER_API_KEY" -n weather
kubectl apply -f deployment-producer.yaml
kubectl apply -f deployment-consumer.yaml
echo "✅ Consumer and prodcuer applications installed to the K8s cluster!"
echo "✅ The scripts has completed!"
