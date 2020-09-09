pushd Gateway
call mvn clean package
call docker build -f src/main/docker/Dockerfile.jvm -t gateway:jvm .
popd

