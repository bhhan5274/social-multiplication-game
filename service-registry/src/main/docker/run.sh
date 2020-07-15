#!/bin/sh

echo "********************************************************"
echo "eureka server to start on port $EUREKA_SERVER_PORT"
echo "********************************************************"
echo "******* Eureka server has started"

java -Dserver.port=$EUREKA_SERVER_PORT \
 -jar /usr/local/service-registry/@project.build.finalName@.jar
