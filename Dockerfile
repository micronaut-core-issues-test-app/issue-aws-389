FROM openjdk:14-alpine
COPY build/libs/issue-aws-389-*-all.jar issue-aws-389.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "issue-aws-389.jar"]