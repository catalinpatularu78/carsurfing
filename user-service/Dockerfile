FROM openjdk:8-jre-alpine3.9
EXPOSE 9092
ADD user-service/target/user-service-0.0.1-SNAPSHOT.jar user-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java","-jar","user-service-0.0.1-SNAPSHOT.jar" ]