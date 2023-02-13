FROM openjdk:8-jre-alpine3.9
EXPOSE 9091
ADD target/userservice-0.0.1-SNAPSHOT.jar userservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java","-jar","userservice-0.0.1-SNAPSHOT.jar" ]