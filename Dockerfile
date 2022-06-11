FROM amazoncorretto:17
LABEL maintainer="olek.gorecki"
ADD build/libs/demo-0.0.1-SNAPSHOT.jar todoapp.jar
ENTRYPOINT ["java", "-jar", "todoapp.jar"]