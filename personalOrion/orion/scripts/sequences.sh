#!/bin/bash
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf
LIB_DIR=$DEPLOY_DIR/lib
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

GALAXY_CORE_JAR=`echo $LIB_DIR/galaxy-instrument*jar`
ASPECTJ_WEAVER_JAR=`echo $LIB_DIR/aspectjweaver*jar`
JAVA_AGENT=" -javaagent:$GALAXY_CORE_JAR -javaagent:$ASPECTJ_WEAVER_JAR "

java -Ddubbo.properties.file=galaxy.properties $JAVA_AGENT -classpath $CONF_DIR:$LIB_JARS com.dcits.galaxy.sequences.SequencesCommand

#java -classpath $CONF_DIR:$LIB_JARS com.dcits.galaxy.business.sequences.SequencesCommand

