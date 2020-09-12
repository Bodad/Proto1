docker build -f gateway/src/main/docker/Dockerfile.jvm -t gateway:jvm gateway
docker build -f order-microservice/src/main/docker/Dockerfile.jvm -t order-microservice:jvm order-microservice
docker build -f product-microservice/src/main/docker/Dockerfile.jvm -t product-microservice:jvm product-microservice
docker build -f dueout-microservice/src/main/docker/Dockerfile.jvm -t dueout-microservice:jvm dueout-microservice