FROM adoptopenjdk/openjdk11:ubi
EXPOSE 8080
ARG JAR_FILE=${env.AZURE_WEBAPP_PACKAGE_PATH}/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
