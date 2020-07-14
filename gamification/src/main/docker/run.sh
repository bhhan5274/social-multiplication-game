#!/bin/sh

echo "********************************************************"
echo "gamification server to start on port $GAMIFICATION_PORT"
echo "********************************************************"

echo "********************************************************"
echo "Waiting for the Social-Multiplication server to start  on port $MULTIPLICATION_SERVER_PORT"
echo "********************************************************"
while ! `nc -z social-multiplication $MULTIPLICATION_SERVER_PORT`; do sleep 10; done
echo "******* Social-Multiplication Server has started"

java -Dserver.port=$GAMIFICATION_PORT \
 -Dspring.rabbitmq.host=$RABBITMQ_HOST \
 -Dspring.rabbitmq.port=$RABBITMQ_PORT \
 -Dspring.rabbitmq.username=$RABBITMQ_USERNAME \
 -Dspring.rabbitmq.password=$RABBITMQ_PASSWORD \
 -jar /usr/local/gamification/@project.build.finalName@.jar
