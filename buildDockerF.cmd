docker build -f gateway/src/main/docker/Dockerfile.fast-jar -t gateway:fast gateway
docker build -f order-microservice/src/main/docker/Dockerfile.fast-jar -t order-microservice:fast order-microservice
docker build -f product-microservice/src/main/docker/Dockerfile.fast-jar -t product-microservice:fast product-microservice
docker build -f dueout-microservice/src/main/docker/Dockerfile.fast-jar -t dueout-microservice:fast dueout-microservice