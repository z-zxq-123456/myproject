@echo off & setlocal enabledelayedexpansion

set LIB_JARS=""
cd ..\lib
for %%i in (*) do set LIB_JARS=!LIB_JARS!;..\lib\%%i
for %%i in (dir /a-d /b "galaxy-instrument*jar") do set GALAXY_CORE_JAR=..\lib\%%i
set JAVA_AGENT=-javaagent:%GALAXY_CORE_JAR%

for %%i in (dir /a-d /b "aspectjweaver*jar") do set GALAXY_ASPECTJ_JAR=..\lib\%%i
set JAVA_AGENT=%JAVA_AGENT% -javaagent:%GALAXY_ASPECTJ_JAR%

echo %JAVA_AGENT%
cd ..\bin

java -Ddubbo.properties.file=galaxy.properties %JAVA_AGENT% -classpath ..\conf;%LIB_JARS% com.dcits.galaxy.business.sequences.SequencesCommand