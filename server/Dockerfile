FROM ubuntu:18.04
RUN apt-get update
RUN apt-get -y install openjdk-11-jdk
ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64
RUN apt -y install maven
ENV JAR_TARGET "demo-0.0.1-SNAPSHOT.jar"
COPY . .
RUN mvn clean package -Dmaven.test.skip
ENTRYPOINT ["java","-jar","/target/demo-0.0.1-SNAPSHOT.jar"]