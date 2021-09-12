# Quarkus Artemis Demo App

## Details
This is a simple java DEMO app which based on Artemis and Quarkus. It opens HTTP socket from 8080 from path "/content/log" for clients. If any request receives, It sends the request's payload to Artemis. Also, it receives all messages from Artemis every 10 seconds. It prints each message which received to console.

## Requirements before run locally
It may work with older or newer versions, but I did not test.

- JDK 11
- Docker 19.03.0
- Maven 3.6.3
- Docker-Compose 3.8
- Curl (only for test)

## Build --> Run --> Test

- Run the services we need to build the docker image

  > ./build_project.sh

- Now we can run all services on docker-compose:

  > ./run.sh

- Now we can test it

  > ./execute_http_request.sh

  Check "docker-compose" log. Maximum in 10 second you should see:
  
  > message (content data) received: I am the client
  
  Text which is sent by HTTP client. You can change this text from "execute_http_request.sh" file.
