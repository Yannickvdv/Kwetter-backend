#!/bin/sh
mvn clean package && docker build -t nl.fhict.jea/kwetter .
docker rm -f kwetter || true && docker run -d -p 8080:8080 -p 4848:4848 --name kwetter nl.fhict.jea/kwetter 
