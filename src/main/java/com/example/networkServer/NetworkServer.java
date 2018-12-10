package com.example.networkServer;
import java.util.*;
import java.io.*;
import java.net.*;
//import NetworkServerRun;

class NetworkServer{
    public static void main(String[] args){
        ServerSocket sock = null;
        Socket client = null;
        try{
            try{
                sock = new ServerSocket(4004);
                System.out.println("Server started!");
                while(true){
                    client = sock.accept();
                    System.out.println("Client connect accepted!");
                    Runnable r = new NetworkServerRun(client); 
                    Thread t = new Thread(r);
                    t.start(); 
                    
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
                sock.close();
                client.close();
            }
        }
        catch(IOException ex){
            System.err.println(ex);
        }
        
        
        
        
        
    }
}
