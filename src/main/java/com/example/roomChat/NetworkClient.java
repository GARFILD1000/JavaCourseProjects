package com.example.roomChat;
import java.util.*;
import java.io.*;
import java.net.*;

class NetworkClient{
    NetworkClient(){
    }
    public void start(){
        Socket sock = null;
        InputStream inStream = null;
        OutputStream outStream = null;
        try{
        try{
            sock = new Socket("localhost",4004);
            System.out.println("Client started!");
            inStream = sock.getInputStream();
            outStream = sock.getOutputStream();
            Scanner fromServer = new Scanner(inStream);
            Scanner in = new Scanner(System.in);
            String readedFromKeyboard = "";
            PrintWriter toServer = new PrintWriter(outStream);
            System.out.println("Wait data to receive:");
            String readedFromServer = fromServer.nextLine();
            System.out.println("Received: " + readedFromServer);
            System.out.println("Client connect accepted!");
            NetworkClientReceiver r = new NetworkClientReceiver(sock, this); 
            Thread t = new Thread(r);
            t.start(); 
            while(!readedFromKeyboard.equals("exit")){
                if (in.hasNextLine()){
                    readedFromKeyboard = in.nextLine();
                    toServer.println(readedFromKeyboard);
                    toServer.flush();
                }
            }
        }
        finally{
            sock.close();
            inStream.close();
            outStream.close();
            System.out.println("I'm out!");
        }
        }
        catch(IOException ex){
            System.err.println(ex);
        }
    }
    
    public void printMessage(String message){
        System.out.println("Message from " + message);
    }
}
