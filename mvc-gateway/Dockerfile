FROM amazoncorretto:11-alpine-jdk as builder

WORKDIR /app/mvc-gateway

COPY ./pom.xml /app
COPY ./mvc-gateway/.mvn ./.mvn
COPY ./mvc-gateway/mvnw .
COPY ./mvc-gateway/pom.xml .


#RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
RUN ./mvnw dependency:go-offline
COPY ./mvc-gateway/src ./src

RUN ./mvnw clean package -DskipTests

FROM amazoncorretto:11-alpine-jdk

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/mvc-gateway/target/mvc-gateway-0.0.1-SNAPSHOT.jar .

EXPOSE 8090
CMD sleep 10 && java -jar mvc-gateway-0.0.1-SNAPSHOT.jar
#CMD ["java", "-jar", "mvc-gateway-0.0.1-SNAPSHOT.jar"]