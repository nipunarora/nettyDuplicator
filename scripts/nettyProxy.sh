#!/usr/bin/env bash

java -cp target/nettyproxy-1.0.jar:target/netty-all-4.1.8.Final.jar org.columbia.parikshan.Main $@
#-l 0.0.0.0:3379 -o 127.0.0.1:3380
#-r 138.15.170.35:3380
