package com.example.networkServer;
import java.net.*;
import java.io.*;

class NetworkServerRun implements Runnable{
    private Socket client;
    private static int iterations = 0;
    private OutputStream outStream;
    
    public NetworkServerRun(Socket newClient){
        client = newClient;
    }
    
    public void run(){
        try{
            try{
                iterations++;
                System.out.printf("Server created new thread for client %d!\n", iterations);
                outStream = client.getOutputStream();
                PrintWriter out = new PrintWriter(outStream);
                String data = "You're number " + iterations;
                System.out.println("Sending...");
                out.println(data);
                out.flush();
                System.out.println("Sended: " + data);
            }
            finally{
                outStream.close();
            }
        }
        catch(IOException ex){
            System.err.println(ex);
        }
    }
}
