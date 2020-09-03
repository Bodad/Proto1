pushd Gateway
docker build -f src/main/docker/Dockerfile.multistage -t proto1 .
popd

