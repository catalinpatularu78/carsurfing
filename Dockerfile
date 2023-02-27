FROM mysql:latest
ENV MYSQL_DATABASE carsurfing
ENV MYSQL_ROOT_PASSWORD=test1234
COPY sql/ /docker-entrypoint-initdb.d/