# Stage 1: Build the project using Maven with JDK 17
FROM maven:3.9.9-ibm-semeru-21-jammy AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
COPY test-suite ./test-suite
RUN mvn clean package -DskipTests

# Stage 2: Run tests using the built project
FROM maven:3.9.9-ibm-semeru-21-jammy
WORKDIR /app
COPY --from=build /app .
CMD ["mvn", "clean", "test", "-DsuiteXmlFile=test-suite/saucedemo/testng_grid.xml"]