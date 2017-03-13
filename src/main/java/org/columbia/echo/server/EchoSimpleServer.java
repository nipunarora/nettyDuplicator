package org.columbia.echo.server;

/**
 * Created by Nipun on 3/13/17.
 */
import java.net.*;
import java.io.*;

import static java.lang.Thread.sleep;

public class EchoSimpleServer
{
    public static void main(String[] args) throws IOException
    {
        if(args.length != 1) {
            System.err.println("Usage: " + EchoServer.class.getSimpleName() + " <port>");
            return;
        }
        ServerSocket serverSocket = null;
        int port = Integer.parseInt(args[0]);

        System.out.println("Listening on port " + port);

        try {
            serverSocket = new ServerSocket(port);
        }
        catch (IOException e)
        {
            System.err.println("Could not listen on port:" + port);
            System.exit(1);
        }

        Socket clientSocket = null;
        System.out.println ("Waiting for connection.....");

        try {
            clientSocket = serverSocket.accept();
        }
        catch (IOException e)
        {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        System.out.println ("Connection successful");
        System.out.println ("Waiting for input.....");

        final Socket finalClientSocket = clientSocket;
        final ServerSocket finalServerSocket = serverSocket;

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    Thread.sleep(200);
                    System.out.println("Shouting down ...");
                    //some cleaning up code...
                    finalClientSocket.close();
                    finalServerSocket.close();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            sleep(50000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*

        final PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
                true);
        final BufferedReader in = new BufferedReader(
                new InputStreamReader( clientSocket.getInputStream()));

        String inputLine;


        while ((inputLine = in.readLine()) != null)
        {
            System.out.println ("Server: " + inputLine);
            out.println(inputLine);

            if (inputLine.equals("Bye."))
                break;
        }


        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
        */
    }
}