FROM amazoncorretto:17-alpine
COPY target/movie-rating-0.0.1-SNAPSHOT.jar movie-rating-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/movie-rating-0.0.1-SNAPSHOT.jar"]