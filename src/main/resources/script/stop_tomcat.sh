#!/bin/bash
#####SET_VER#####
BASEDIR=/home/work/house
SER_NAME=house-feedback
SHUTDOWN_PORT=7367
HTTP_PORT=8367
AJP_PORT=9367
SER_BASE_R_TMP=${BASEDIR}/${SER_NAME}
SER_BASE_R="${SER_BASE_R_TMP//\//\\/}"
#####NO_NEED_2_SET#####
TOMCAT_NAME=tomcat_$HTTP_PORT
SER_BASE=$BASEDIR/$SER_NAME
SRCFILE=/home/work/tmp/template

#####START#####
echo start
mkdir -p $SER_BASE
if [ ! -d "$BASEDIR/$TOMCAT_NAME" ]; then
 echo do not exist $BASEDIR/$TOMCAT_NAME
 exit 0
else
 $BASEDIR/$TOMCAT_NAME/bin/stop_tomcat.sh
 exit $?
fi
