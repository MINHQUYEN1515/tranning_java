FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/demo-0.0.1-SNAPSHOT.jar app.jar
VOLUME /demo/src/main/resources/static/image/
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
# run docker cmd
# --------CONNECT DATABASE MYSQL
# docker create network demo-network
# docker run --network demo-network --name redis -p 6379:6379 redis/redis-stack-server:latest
# docker run --network demo-network --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=${Nhập password của bạn ở đây} -d mysql
# docker run --name demo --network demo-network -p 8080:8080 -e DB_CONNECT=jdbc:mysql://mysql:3306/tranning -e MYSQL=true -e REDIS_HOST=redis demo
#
# Use an official Maven image as the base image
