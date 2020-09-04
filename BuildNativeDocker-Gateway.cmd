pushd Gateway
docker build -f src/main/docker/Dockerfile.multistage -t gateway .
popd

