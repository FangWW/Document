set currpath=.\
if "%OS%" == "Windows_NT" set currpath=%~dp0%
cd %currpath%


set classpath=lib/axis.jar;lib/commons-discovery.jar;lib/commons-logging.jar;lib/jaxrpc.jar;lib/saaj.jar;lib/log4j-1.2.8.jar;lib/xml-apis.jar;lib/xerces.jar

javac  -d %currpath%classes %currpath%src\mypack\HelloService.java
javac  -d %currpath%classes %currpath%src\mypack\HelloClient.java

