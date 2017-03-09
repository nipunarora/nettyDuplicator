package org.columbia.parikshan;

import org.columbia.parikshan.duplicator.Duplicator;
import org.columbia.parikshan.proxy.NettyProxy;

/**
 * Created by Nipun on 3/8/17.
 */
public class Main {
    public static boolean debug = false;

    public static void main(String args[]) throws Exception {
        String out = "127.0.0.1";
        String listenIP = "0.0.0.0";
        int port = 1358;
        int listenPort = 1357;
        boolean duplicate = false;
        String replica = "127.0.0.1";
        int replicaPort = 1356;

        if(args.length==1){
            if(args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("--help")) {
                System.out.println("-l or --listen <listenIP>:<listenPort>");
                System.out.println("-o or --output <targetIP>:<targetPort>");
                System.out.println("-r or --replica <replicaIP>:<replicaPort>");
            }
        }

        // Parse through arguments.
        for(int i = 0; i < args.length; i++) {
            // Look for out (-o, --out)
            if(args[i].equalsIgnoreCase("-l") || args[i].equalsIgnoreCase("--listen")) {

                String[] split = args[i + 1].split(":");
                listenIP=split[0];
                listenPort = Integer.valueOf(split[1]);
            }
            if(args[i].equalsIgnoreCase("-o") || args[i].equalsIgnoreCase("--output")) {
                String[] split = args[i + 1].split(":");
                out=split[0];
                port = Integer.valueOf(split[1]);
            }
            if(args[i].equalsIgnoreCase("-r") || args[i].equalsIgnoreCase("--replica")) {
                duplicate = true;
                String[] split = args[i + 1].split(":");
                replica=split[0];
                replicaPort = Integer.valueOf(split[1]);
            }
            // Look for debug (-d, --debug)
            if(args[i].equalsIgnoreCase("-d") || args[i].equalsIgnoreCase("--debug")) {
                debug = true;
                System.out.println("Debug mode activated.");
            }
        }

        if(duplicate)
            Duplicator.execute(listenPort,out,port,replica,replicaPort);
        else
            NettyProxy.execute(listenPort,out,port);

    }
}
