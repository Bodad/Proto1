docker service create --network proto1_swarm --name dueout-microservice dueout-microservice:jvm
docker service create --network proto1_swarm --name product-microservice product-microservice:jvm
docker service create --network proto1_swarm --name order-microservice order-microservice:jvm
