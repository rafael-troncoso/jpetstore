FROM oracle/glassfish

MAINTAINER Rafael Troncoso<rafael.troncoso@salientcrgt.com>

ADD target/jpetstore.war /glassfish4/glassfish/domains/domain1/autodeploy

