set currpath=.\
if "%OS%" == "Windows_NT" set currpath=%~dp0%

set classpath=%currpath%lib\mysqldriver.jar;%currpath%classes

start rmiregistry
start java -Djava.rmi.server.codebase=file:///%currpath%classes\  store.StoreServer
