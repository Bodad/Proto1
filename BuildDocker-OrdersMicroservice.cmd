pushd OrderMicroservice
call mvn clean package
call docker build -f src/main/docker/Dockerfile.jvm -t order-microservice .
popd

