FROM openjdk:8-jre-alpine3.9
EXPOSE 9091
ADD ride-service/target/ride-service-0.0.1-SNAPSHOT.jar ride-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java","-jar","ride-service-0.0.1-SNAPSHOT.jar" ]
