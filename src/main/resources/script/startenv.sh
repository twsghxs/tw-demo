#!/bin/sh

export TOMCAT_USER="work"
export JAVA_OPTS="-server -Xms1536m -Xmx1536m -XX:NewRatio=2 -XX:PermSize=256M -XX:MaxPermSize=512M -verbose:GC  -XX:+PrintGCDetails  -XX:+PrintGCDateStamps  -XX:+PrintGCApplicationStoppedTime  -Xloggc:${CATALINA_BASE}/logs/gc.log  -XX:+HeapDumpOnOutOfMemoryError"
