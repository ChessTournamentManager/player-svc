FROM maven:3.8.6-openjdk-18-slim

ADD target/player-svc.jar player-svc.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/player-svc.jar"]
