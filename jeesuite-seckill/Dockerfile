FROM 127.0.0.1:5000/tomcat:8.0
ADD demo-rest/target/demo-rest.war /usr/local/tomcat/webapps/
RUN  mkdir -p /usr/local/tomcat/webapps/ROOT && unzip /usr/local/tomcat/webapps/demo-rest.war -d /usr/local/tomcat/webapps/ROOT &&  rm -f /usr/local/tomcat/webapps/demo-rest.war && chown www. /usr/local/tomcat/webapps/ -R
WORKDIR /usr/local/tomcat 
CMD ["bin/catalina.sh", "run"]
