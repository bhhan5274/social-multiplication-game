#!/bin/sh

echo "********************************************************"
echo "social-multiplication server to start on port $MULTIPLICATION_SERVER_PORT"
echo "********************************************************"

java -Dserver.port=$MULTIPLICATION_SERVER_PORT -jar /usr/local/social-multiplication/@project.build.finalName@.jar
