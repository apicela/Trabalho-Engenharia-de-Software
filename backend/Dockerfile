FROM gradle:jdk22

COPY . /app

WORKDIR /app

RUN gradle build

CMD ["java", "-jar", "build/libs/engsoft-0.0.1-SNAPSHOT.jar"]