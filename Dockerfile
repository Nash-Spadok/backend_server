FROM openjdk:21-jdk-slim AS builder
RUN apt-get update && \
    apt-get install -y wget && \
    wget https://downloads.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.tar.gz && \
    tar -xvzf apache-maven-3.9.5-bin.tar.gz -C /opt && \
    ln -s /opt/apache-maven-3.9.5 /opt/maven && \
    ln -s /opt/maven/bin/mvn /usr/bin/mvn
WORKDIR /application
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests -Dcheckstyle.skip=true

FROM openjdk:21-jdk AS builder2
WORKDIR /application
COPY --from=builder /application/target/*.jar application.jar
ENV SPRING_PROFILES_ACTIVE=local
RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:21-jdk
WORKDIR /application
COPY --from=builder2 /application/dependencies/ ./
COPY --from=builder2 /application/spring-boot-loader/ ./
COPY --from=builder2 /application/snapshot-dependencies/ ./
COPY --from=builder2 /application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
EXPOSE 8080
