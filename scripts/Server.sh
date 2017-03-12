#!/usr/bin/env bash

java -cp target/nettyproxy-1.0.jar:target/netty-all-4.1.8.Final.jar org.columbia.echo.server.EchoServer $@
