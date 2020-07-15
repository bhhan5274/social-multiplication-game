#!/bin/sh

echo "********************************************************"
echo "gateway server to start on port $GATEWAY_SERVER_PORT"
echo "********************************************************"

echo "********************************************************"
echo "Waiting for the Eureka server to start  on port $EUREKA_SERVER_PORT"
echo "********************************************************"
while ! `nc -z service-registry $EUREKA_SERVER_PORT`; do sleep 10; done
echo "******* Eureka server has started"

java -Dserver.port=$GATEWAY_SERVER_PORT \
 -Deureka.client.serviceUrl.defaultZone=$EUREKA_SERVER_URI \
 -jar /usr/local/gateway/@project.build.finalName@.jar
