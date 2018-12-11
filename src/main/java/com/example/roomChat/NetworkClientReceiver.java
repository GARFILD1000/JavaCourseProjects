package com.example.roomChat;
import java.net.*;
import java.io.*;
import java.util.*;


class NetworkClientReceiver implements Runnable{
    private Socket socket;
    private Scanner fromServer;
    private InputStream inStream;
    private NetworkClient client;
    public NetworkClientReceiver(Socket newSocket, NetworkClient thisClient){
        socket = newSocket;
        client = thisClient;
    }
    public void run(){
        try{
            try{
                inStream = socket.getInputStream();
                fromServer = new Scanner(inStream);
                String stringFromServer;
                while(true){
                    if(fromServer.hasNextLine()){
                        stringFromServer = fromServer.nextLine();
                        client.printMessage(stringFromServer);
                    }
                }
            }
            finally{
                inStream.close();
            }
        }
        catch(IOException ex){
            System.err.println(ex);
        }
    }




}
