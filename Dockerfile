FROM eclipse-temurin:17.0.12_7-jre

WORKDIR /root

# Copia el JAR ya construido desde tu máquina local
COPY ./build/libs/plazoleta-0.0.1-SNAPSHOT.jar ./plazoleta-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/root/plazoleta-0.0.1-SNAPSHOT.jar"]
