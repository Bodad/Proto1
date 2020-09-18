docker build -f gateway/src/main/docker/Dockerfile.multistage -t gateway:native --build-arg MODULE=gateway .
docker build -f order-microservice/src/main/docker/Dockerfile.multistage -t order-microservice:native --build-arg MODULE=order-microservice .
docker build -f product-microservice/src/main/docker/Dockerfile.multistage -t product-microservice:native --build-arg MODULE=product-microservice .
docker build -f dueout-microservice/src/main/docker/Dockerfile.multistage -t dueout-microservice:native --build-arg MODULE=dueout-microservice .