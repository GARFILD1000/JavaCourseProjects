package com.example.roomChat;
import java.util.*;
import java.io.*;
import java.net.*;
//import NetworkServerRun;

class NetworkServer{
    public LinkedList<NetworkServerRun> room;
    
    {
        room = new LinkedList<NetworkServerRun>();
    }
    public NetworkServer(){
    
    }
    
    public void startServer(){
        ServerSocket serverSocket = null;
        Socket client = null;
        try{
            try{
                serverSocket = new ServerSocket(4004);
                System.out.println("Server started!");
                while(true){
                    client = serverSocket.accept();
                    System.out.println("Client connect accepted!");
                    NetworkServerRun r = new NetworkServerRun(client, this); 
                    Thread t = new Thread(r);
                    t.start(); 
                    room.add(r);
                   /* String data = "";
                PrintWriter out = new PrintWriter(outStream);
                while(data != "exit server"){
                    data = input.nextLine();
                    System.out.println("Sending...");
                    out.println(data);
                    out.flush();
                    System.out.println("Sended: " + data);*/
                }
            }
            finally{
                serverSocket.close();
                client.close();
            }
        }
        catch(IOException ex){
            System.err.println(ex);
        }
    }
    
    public void sendMessageToRoom(String message){
        System.out.println("Sending message to room");
        for(NetworkServerRun x: room){
            x.sendMessage(message);
        }
    }
}
