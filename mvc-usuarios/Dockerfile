FROM amazoncorretto:11-alpine-jdk as builder

WORKDIR /app/mvc-usuarios

COPY ./pom.xml /app
COPY ./mvc-usuarios/.mvn ./.mvn
COPY ./mvc-usuarios/mvnw .
COPY ./mvc-usuarios/pom.xml .


RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline
COPY ./mvc-usuarios/src ./src

RUN ./mvnw clean package -DskipTests

FROM amazoncorretto:11-alpine-jdk

WORKDIR /app

COPY --from=builder /app/mvc-usuarios/target/mvc-usuarios-0.0.1-SNAPSHOT.jar .

ENV PORT 8000

EXPOSE $PORT

CMD ["java", "-jar", "mvc-usuarios-0.0.1-SNAPSHOT.jar"]