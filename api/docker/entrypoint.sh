#! /usr/bin/env bash

# 启动 SSHD
/usr/sbin/sshd


mvn clean package -Dmaven.test.skip=true

java -jar /workspace/target/code-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod 



tail -f /dev/null