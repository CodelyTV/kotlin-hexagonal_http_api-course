FROM gradle:7.5.0-jdk11-alpine as builder
USER root
WORKDIR /builder
ADD . /builder
RUN gradle build

FROM openjdk:11.0.16-jre-slim
WORKDIR /app
EXPOSE 8080
COPY --from=builder /builder/build/libs/02-02-create-course-with-validations-0.0.1.jar .
CMD ["java", "-jar", "02-02-create-course-with-validations-0.0.1.jar"]

