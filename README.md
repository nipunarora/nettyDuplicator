Compile and install:

mvn package


Run the proxy:

java -jar target/nettyProxy.jar

Run iperf for the test:

Server 1: iperf -s -p 3379
Server 2: iperf -s -p 3380
Client : iperf -c 127.0.0.1 -p 3378