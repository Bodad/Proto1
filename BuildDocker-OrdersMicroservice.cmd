pushd order-service
call mvn clean package
call docker build -f src/main/docker/Dockerfile.jvm -t orders-microservice .
popd

