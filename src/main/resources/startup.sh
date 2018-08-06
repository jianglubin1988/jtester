#!/bin/sh
LIB_PATH=$CLASSPATH:../lib/commons-beanutils-1.8.0.jar:../lib/commons-codec-1.9.jar:../lib/commons-collections-3.2.1.jar:../lib/commons-lang-2.5.jar:../lib/commons-logging-1.1.1.jar:../lib/ezmorph-1.0.6.jar:../lib/httpclient-4.5.3.jar:../lib/httpcore-4.4.6.jar:../lib/json-../lib-2.4-jdk15.jar:../lib/log4j-1.2.17.jar:../lib/slf4j-api-1.7.7.jar:../lib/slf4j-log4j12-1.7.7.jar

echo $LIB_PATH
java -cp $LIB_PATH com.bin.main.Tester
