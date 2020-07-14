#!/bin/sh

echo "********************************************************"
echo "social-multiplication server to start on port $MULTIPLICATION_SERVER_PORT"
echo "********************************************************"

echo "********************************************************"
echo "Waiting for the RabbitMQ server to start  on port $RABBITMQ_PORT"
echo "********************************************************"
while ! `nc -z rabbitmq $RABBITMQ_PORT`; do sleep 10; done
echo "******* RabbitMQ has started"

java -Dserver.port=$MULTIPLICATION_SERVER_PORT \
 -Dspring.rabbitmq.host=$RABBITMQ_HOST \
 -Dspring.rabbitmq.port=$RABBITMQ_PORT \
 -Dspring.rabbitmq.username=$RABBITMQ_USERNAME \
 -Dspring.rabbitmq.password=$RABBITMQ_PASSWORD \
 -jar /usr/local/social-multiplication/@project.build.finalName@.jar
