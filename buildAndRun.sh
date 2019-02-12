#!/bin/sh
mvn clean package && docker build -t com.mycompany/Kwetter .
docker rm -f Kwetter || true && docker run -d -p 8080:8080 -p 4848:4848 --name Kwetter com.mycompany/Kwetter 
