#!/bin/bash
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf

SERVER_NAME=`sed '/galaxy.application.name/!d;s/.*=//' conf/galaxy.properties | tr -d '\r'`
SERVER_PROTOCOL=`sed '/galaxy.protocol.name/!d;s/.*=//' conf/galaxy.properties | tr -d '\r'`
SERVER_PORT=`sed '/jetty.port/!d;s/.*=//' conf/galaxy.connector.properties | tr -d '\r'`
LOGS_FILE=`sed '/galaxy.log4j.file/!d;s/.*=//' conf/galaxy.properties | tr -d '\r'`

if [ -z "$SERVER_NAME" ]; then
    SERVER_NAME=`hostname`
fi

PIDS=`ps -ef | grep java | grep "galaxy.application.name=$SERVER_NAME" |awk '{print $2}'`
if [ -n "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME already started!"
    echo "PID: $PIDS"
    exit 1
fi

if [ -n "$SERVER_PORT" ]; then
    SERVER_PORT_COUNT=`netstat -tln | grep $SERVER_PORT | wc -l`
    if [ $SERVER_PORT_COUNT -gt 0 ]; then
        echo "ERROR: The $SERVER_NAME port $SERVER_PORT already used!"
        exit 1
    fi
fi

LOGS_DIR=""
if [ -n "$LOGS_FILE" ]; then
    LOGS_DIR=`dirname $LOGS_FILE`
else
    LOGS_DIR=$DEPLOY_DIR/logs
fi
if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi
STDOUT_FILE=$LOGS_DIR/stdout.log

LIB_DIR=$DEPLOY_DIR/lib
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

JAVA_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "
JAVA_DEBUG_OPTS=""
if [ "$1" = "debug" ]; then
    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "
fi
JAVA_JMX_OPTS=""
if [ "$1" = "jmx" ]; then
    JAVA_JMX_OPTS=" -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "
fi
JAVA_MEM_OPTS=" -server -Xmx6g -Xms6g -Xmn4g -XX:PermSize=256m -Xss256k "
GALAXY_CORE_JAR=`echo $LIB_DIR/galaxy-instrument-*jar`
JAVA_AGENT="-javaagent:$GALAXY_CORE_JAR"

nohup java -Ddubbo.properties.file=galaxy.properties -Dgalaxy.application.name=$SERVER_NAME $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS -classpath $CONF_DIR:$LIB_JARS $JAVA_AGENT com.dcits.galaxy.core.platform.GalaxyStarter > $STDOUT_FILE 2>&1 &

echo "OK!"
PIDS=`ps -ef | grep java | grep "galaxy.application.name=$SERVER_NAME" | awk '{print $2}'`
echo "PID: $PIDS"
echo "STDOUT: $STDOUT_FILE"
