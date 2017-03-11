#!/usr/bin/env bash

java -cp target/nettyproxy-1.0.jar:target/netty-all-4.1.8.Final.jar org.columbia.parikshan.Main -l 0.0.0.0:1357 -o 127.0.0.1:1358 -r 127.0.0.1:1356