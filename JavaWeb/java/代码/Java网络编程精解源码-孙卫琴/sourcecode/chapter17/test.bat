set currpath=.\
if "%OS%" == "Windows_NT" set currpath=%~dp0%
cd %currpath%

set classpath=%currpath%;lib/axis.jar;lib/commons-discovery.jar;lib/commons-logging.jar;lib/jaxrpc.jar;lib/saaj.jar;lib/log4j-1.2.8.jar;lib/xml-apis.jar;lib/xerces.jar


rem this assumes webserver is running on port 8080

@echo .
@echo 1. deploy helloservice
java org.apache.axis.client.AdminClient deploy.wsdd 

@echo .
@echo 2.HelloClient accesses helloservice...
java mypack.HelloClient weiqin 

@echo .
@echo 3.Now undeploy helloservice
java org.apache.axis.client.AdminClient undeploy.wsdd 
