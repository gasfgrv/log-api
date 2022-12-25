FROM openjdk:11-jdk
RUN addgroup --system spring && adduser --system spring && adduser spring spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java -Dspring.datasource.password=${MARIADB_PASS} -Dspring.datasource.username=${MARIADB_USER} -jar /app.jar"]