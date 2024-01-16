FROM openjdk:17
EXPOSE 8080
ADD target/newsapp-springboot-0.0.1-SNAPSHOT.jar newsapp-springboot-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/newsapp-springboot-0.0.1-SNAPSHOT.jar"]