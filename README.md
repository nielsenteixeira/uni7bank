# uni7Bank
An experiment of Spring Data for the discipline of persistence of postgraduate course

#### Prerequisites
- [OpenJDK 11](https://openjdk.java.net/projects/jdk/11/)
- [Maven 3.6+](https://maven.apache.org/install.html)

#### Running

- Before running application is required configure Postgres database on port 5432 and create database 'uni7Bank'
- If you prefer run database into [Docker](https://docs.docker.com/install/linux/docker-ee/ubuntu/):
```
docker run --name uni7Bank -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=ecommerce postgres:9.6-alpine
```
```
psql -h localhost -U postgres -d postgres;
```
```
create database uni7Bank;
```


After this you can run the application:
```
mvn clean compile spring-boot:run
```

The TestRun class is annotated as @Configuration and has a method that returns a bean of type CommandLineRunner to perform the operations on the database.
