#!/bin/bash

echo "🧹 Starting cleanup..."

echo "📝 Deleting resources from minikube cluster"
minikube delete
echo "✅ Done!"

echo "📝 Cleaning up Maven build artifacts..."
cd weather-consumer-app
mvn clean -q
cd ../weather-producer-app
mvn clean -q
cd ..
echo "✅ Done!"

echo "📝 Removing Docker images..."
docker rmi weather-producer-app weather-consumer-app -f 2>/dev/null || true
echo "✅ Done!"

echo "✅ Cleanup completed!"
