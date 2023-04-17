FROM amazoncorretto:11-alpine-jdk as builder

ARG MVC_NAME=mvc-usuarios

WORKDIR /app/$MVC_NAME

COPY ./pom.xml /app
COPY ./$MVC_NAME/.mvn ./.mvn
COPY ./$MVC_NAME/mvnw .
COPY ./$MVC_NAME/pom.xml .


RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline
COPY ./$MVC_NAME/src ./src

RUN ./mvnw clean package -DskipTests

FROM amazoncorretto:11-alpine-jdk

WORKDIR /app
ARG MVC_NAME=mvc-usuarios
ARG TARGET_FOLDER=/app/$MVC_NAME/target
COPY --from=builder $TARGET_FOLDER/mvc-usuarios-0.0.1-SNAPSHOT.jar .

ARG PORT_APP=8001

ENV PORT $PORT_APP

EXPOSE $PORT
CMD sleep 20 && java -jar mvc-usuarios-0.0.1-SNAPSHOT.jar
#CMD ["java", "-jar", "mvc-usuarios-0.0.1-SNAPSHOT.jar"]