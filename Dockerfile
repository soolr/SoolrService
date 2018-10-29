FROM openjdk:8u121-jre
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ADD target/DingPortalService-1.0-SNAPSHOT.jar /DingPortalService-1.0-SNAPSHOT.jar
CMD java -jar /DingPortalService-1.0-SNAPSHOT.jar
