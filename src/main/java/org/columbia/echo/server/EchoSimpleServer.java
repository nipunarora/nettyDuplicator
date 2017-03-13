package org.columbia.echo.server;

/**
 * Created by Nipun on 3/13/17.
 */
import java.net.*;
import java.io.*;

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

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
                true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader( clientSocket.getInputStream()));

        String inputLine;

        /*
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