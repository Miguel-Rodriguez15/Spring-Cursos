FROM amazoncorretto:11.0.18

WORKDIR /app

COPY ./target/mvc-usuarios-0.0.1-SNAPSHOT.jar .

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "mvc-usuarios-0.0.1-SNAPSHOT.jar"]