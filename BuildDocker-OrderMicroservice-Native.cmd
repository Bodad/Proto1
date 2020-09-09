pushd OrderMicroservice
docker build -f src/main/docker/Dockerfile.multistage -t order-microservice:native .
popd

