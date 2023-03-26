FROM openjdk:8-jre-alpine3.9
EXPOSE 9099
ADD review-service/target/review-service-0.0.1-SNAPSHOT.jar review-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java","-jar","review-service-0.0.1-SNAPSHOT.jar" ]
