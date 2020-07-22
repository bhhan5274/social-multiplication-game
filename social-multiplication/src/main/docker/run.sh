#!/bin/sh

echo "********************************************************"
echo "social-multiplication server to start on port $MULTIPLICATION_SERVER_PORT"
echo "********************************************************"

echo "********************************************************"
echo "Waiting for the RabbitMQ server to start  on port $RABBITMQ_PORT"
echo "********************************************************"
while ! `nc -z rabbitmq $RABBITMQ_PORT`; do sleep 10; done
echo "******* RabbitMQ has started"

echo "********************************************************"
echo "Waiting for the Eureka server to start  on port $EUREKA_SERVER_PORT"
echo "********************************************************"
while ! `nc -z service-registry $EUREKA_SERVER_PORT`; do sleep 3; done
echo "******* Eureka Server has started"

echo "********************************************************"
echo "Waiting for the gateway server to start  on port $GATEWAY_SERVER_PORT"
echo "********************************************************"
while ! `nc -z gateway $GATEWAY_SERVER_PORT`; do sleep 3; done
echo "******* Gateway Server has started"

java -Dserver.port=$MULTIPLICATION_SERVER_PORT \
 -Dspring.rabbitmq.host=$RABBITMQ_HOST \
 -Dspring.rabbitmq.port=$RABBITMQ_PORT \
 -Dspring.rabbitmq.username=$RABBITMQ_USERNAME \
 -Dspring.rabbitmq.password=$RABBITMQ_PASSWORD \
 -Deureka.client.serviceUrl.defaultZone=$EUREKA_SERVER_URI \
 -Dspring.profiles.active=$PROFILE \
 -jar /usr/local/social-multiplication/@project.build.finalName@.jar
