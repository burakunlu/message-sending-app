#!/bin/bash
#set -x

mvn clean install
java -jar target/message-sending-app-1.0.0-SNAPSHOT.jar multi hello user2____