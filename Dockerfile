FROM maven:3.8.1-jdk-11 as build
LABEL maintainer =" hicham"  
WORKDIR /app
ADD ./ /app/
RUN mvn -version  \
&& mvn package

FROM tomcat
LABEL maintainer =" hicham"
COPY --from=build /app/target/jwt-authentication-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
