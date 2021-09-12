./mvnw clean
./mvnw package
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/quarkus_artemis_demo_app-jvm .

