FROM java:8
EXPOSE 8090:8090
ADD /target/cmaDocker-Api.jar cmaDocker-Api.jar
ENTRYPOINT ["java", "-jar", "cmaDocker-Api.jar"]