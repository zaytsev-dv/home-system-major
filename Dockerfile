FROM maven:3.6-jdk-8-alpine AS builder

ARG ARTIFACTORY_USER
ARG ARTIFACTORY_PASSWORD

WORKDIR /src
COPY . .
RUN mvn -B clean install -Dartifactory.user=${ARTIFACTORY_USER} -Dartifactory.password=${ARTIFACTORY_PASSWORD} -Dbuildnumber=1 -s settings.xml

FROM java:8-jre-alpine
COPY --from=builder /src/web/target/home-system-major.jar /home-system-major.jar
ENTRYPOINT ["java","-jar","home-system-major.jar"]


#docker build -t image_name .
#docker run  --network network_name -e "SPRING_PROFILES_ACTIVE=dev"-p 8080:8080 -d --name container_name image_name
#docker run --network skynet -e "SPRING_PROFILES_ACTIVE=dev" -p 8080:8080 config-server

