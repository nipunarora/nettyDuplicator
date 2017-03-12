package org.columbia.echo.client;

import java.io.*;
import java.net.*;

public class EchoSimpleClient
{
    public static void main(String args[]) throws Exception
    {
        if(args.length != 2) {
            System.err.println("Usage: " + EchoClient.class.getSimpleName() + " <host> <port>");
            return;
        }

        final String host = args[0];
        final int port = Integer.parseInt(args[1]);

        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
        Socket clientSocket = new Socket(host, port);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while(true) {
            sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');
            modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + modifiedSentence);
        }
        
        //clientSocket.close();
    }
}