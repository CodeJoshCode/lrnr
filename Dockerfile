FROM openjdk:11.0.10-oracle
COPY target/lrnr-0.0.1-SNAPSHOT.jar lrnr-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/lrnr-0.0.1-SNAPSHOT.jar"]