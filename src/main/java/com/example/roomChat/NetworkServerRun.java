package com.example.roomChat;
import java.net.*;
import java.io.*;
import java.util.*;

class NetworkServerRun implements Runnable{
    private Socket client;
    private static int iterations = 0;
    public int ID;
    private OutputStream outStream;
    private InputStream inStream;
    private NetworkServer server;
    private Scanner fromClient;
    private PrintWriter out;
    
    public NetworkServerRun(Socket newClient, NetworkServer thisServer){
        client = newClient;
        server = thisServer;
    }
    
    public void run(){
        try{
            try{
                iterations++;
                ID = iterations;
                System.out.printf("Server created new thread for client %d!\n", iterations);
                outStream = client.getOutputStream();
                inStream = client.getInputStream();
                out = new PrintWriter(outStream);
                fromClient = new Scanner(inStream);
                String data = "You're number " + iterations;
                out.println(data);
                out.flush();
                String stringFromClient = "";
                while(true){
                    if(fromClient.hasNextLine()){
                        stringFromClient = fromClient.nextLine();
                        System.out.println("Client " + ID + " :" + stringFromClient);
                        server.sendMessageToRoom(ID + " : " + stringFromClient);
                    }
                }
            }
            finally{
                outStream.close();
                inStream.close();
            }
        }
        catch(IOException ex){
            System.err.println(ex);
        }
    }
    
    public void sendMessage(String message){
        System.out.println(ID + " : " + message);
        try{
            out.println(message);
            out.flush();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
