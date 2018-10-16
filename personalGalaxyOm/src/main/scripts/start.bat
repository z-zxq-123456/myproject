@echo off & setlocal enabledelayedexpansion

set LIB_JARS=""
cd ..\lib
for %%i in (*) do set LIB_JARS=!LIB_JARS!;..\lib\%%i
for %%i in (dir /a-d /b "galaxy-instrument*jar") do set GALAXY_CORE_JAR=..\lib\%%i
for %%i in (dir /a-d /b "aspectjweaver*jar") do set ASPECTJ_WEAVER_JAR=..\lib\%%i
set JAVA_AGENT=-javaagent:%GALAXY_CORE_JAR% -javaagent:%ASPECTJ_WEAVER_JAR%

cd ..\bin

if ""%1"" == ""debug"" goto debug
if ""%1"" == ""jmx"" goto jmx

java -Ddubbo.properties.file=galaxy.properties -Xms64m -Xmx1024m -XX:MaxPermSize=64M -classpath ..\conf;%LIB_JARS% %JAVA_AGENT% com.dcits.dynamic.web.server.JettyAppMain /ensemble-om
goto end

:debug
java -Ddubbo.properties.file=galaxy.properties -Xms64m -Xmx1024m -XX:MaxPermSize=64M -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n -classpath ..\conf;%LIB_JARS% %JAVA_AGENT% com.dcits.dynamic.web.server.JettyAppMain /ensemble-om
goto end

:jmx
java -Ddubbo.properties.file=galaxy.properties -Xms64m -Xmx1024m -XX:MaxPermSize=64M -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -classpath ..\conf;%LIB_JARS% %JAVA_AGENT% com.dcits.dynamic.web.server.JettyAppMain /ensemble-om

:end
pause