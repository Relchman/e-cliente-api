FROM registry.ccarj.intraer/mirror/library/amazoncorretto:8 AS builder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM registry.ccarj.intraer/mirror/library/amazoncorretto:8
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./

ARG SPRING_PROFILE
RUN echo "SPRING_PROFILE: $SPRING_PROFILE"
ENV SPRING_PROFILES_ACTIVE=$SPRING_PROFILE

ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "org.springframework.boot.loader.JarLauncher"]

