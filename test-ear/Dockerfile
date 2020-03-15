FROM jboss/wildfly:18.0.1.Final

RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#70365 --silent

ADD target/test-ear.ear /opt/jboss/wildfly/standalone/deployments/

#CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "-c", "standalone.xml", "--debug", "*:8787"]
