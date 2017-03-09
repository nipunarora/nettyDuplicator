Compile and install:

>mvn package


Run the proxy:

>java -jar target/Proxy.jar -l 0.0.0.0:3378 -o 127.0.0.1:3379 -r 127.0.0.1:3380

Run iperf for the test:

>Server 1: iperf -s -p 3379

>Server 2: iperf -s -p 3380

>Client : iperf -c 127.0.0.1 -p 3378