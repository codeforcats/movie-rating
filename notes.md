# Movie Info Service Notes

## make the executable jar file
* ./mvnw package

## then, EITHER
## run jar file from the command line
java -jar target/movie-rating-service-0.0.1-SNAPSHOT.jar

## OR
## first build a docker image which runs the executable jar file
* docker build -t movie-rating-service-image .

## then run (detached) the image in a docker container, with ports mapped.
* docker run --name movie-rating-service-container -d -p 8083:8083 movie-rating-service-image 

## REGARDLESS of running in Docker or not

## test the application (for movie ratings found)
* curl http://localhost:8083/movieRatings/joe

## test the application (for no movie ratings not found)
* curl http://localhost:8083/movieRatings/jen

## test the application (for unkown user)
* curl http://localhost:8083/movieRatings/foo