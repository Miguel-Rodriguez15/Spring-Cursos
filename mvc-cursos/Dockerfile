FROM amazoncorretto:11-alpine-jdk as builder

WORKDIR /app/mvc-cursos

COPY ./pom.xml /app
COPY ./mvc-cursos/.mvn ./.mvn
COPY ./mvc-cursos/mvnw .
COPY ./mvc-cursos/pom.xml .


#RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
RUN ./mvnw dependency:go-offline
COPY ./mvc-cursos/src ./src

RUN ./mvnw clean package -DskipTests

FROM amazoncorretto:11-alpine-jdk

WORKDIR /app

COPY --from=builder /app/mvc-cursos/target/mvc-cursos-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

CMD ["java", "-jar", "mvc-cursos-0.0.1-SNAPSHOT.jar"]