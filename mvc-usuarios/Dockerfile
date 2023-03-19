FROM amazoncorretto:11.0.18

WORKDIR /app/mvc-usuarios

COPY ./pom.xml /app
COPY ./mvc-usuarios/.mvn ./.mvn
COPY ./mvc-usuarios/mvnw .
COPY ./mvc-usuarios/pom.xml .


RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline
COPY ./mvc-usuarios/src ./src

RUN ./mvnw clean package -DskipTests


EXPOSE 8001

ENTRYPOINT ["java", "-jar", "./target/mvc-usuarios-0.0.1-SNAPSHOT.jar"]