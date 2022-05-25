FROM amazoncorretto:17-alpine
COPY target/movie-rating-service-0.0.1-SNAPSHOT.jar movie-rating-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/movie-rating-service-0.0.1-SNAPSHOT.jar"]