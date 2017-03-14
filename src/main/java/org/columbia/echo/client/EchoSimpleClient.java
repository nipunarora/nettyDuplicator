package org.columbia.echo.client;

import java.io.*;
import java.net.*;

public class EchoSimpleClient
{
    public static void main(String args[]) throws Exception
    {

        int counter = 0;
        if(args.length != 2) {
            System.err.println("Usage: " + EchoClient.class.getSimpleName() + " <host> <port>");
            return;
        }

        final String host = args[0];
        final int port = Integer.parseInt(args[1]);

        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
        final Socket clientSocket = new Socket(host, port);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    Thread.sleep(200);
                    System.out.println("Shouting down ...");
                    //some cleaning up code...
                    clientSocket.close();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        sentence = inFromUser.readLine();

        while(true) {
            System.out.println(++counter + ".Send to server " + sentence);
            outToServer.writeBytes(sentence + '\n');
            outToServer.flush();
            //modifiedSentence = inFromServer.readLine();
            //System.out.println("FROM SERVER: " + modifiedSentence);
        }

        //clientSocket.close();
    }
}