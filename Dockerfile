FROM maven:latest

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY . .

CMD ["mvn", "test"]