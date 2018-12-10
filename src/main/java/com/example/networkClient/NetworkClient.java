package com.example.networkClient;
import java.util.*;
import java.io.*;
import java.net.*;

class NetworkClient{
    public static void main(String[] args){
        Socket sock = null;
        InputStream inStream = null;
        OutputStream outStream = null;
        try{
        try{
            sock = new Socket("localhost",4004);
            System.out.println("Client started!");
            inStream = sock.getInputStream();
            outStream = sock.getOutputStream();
            Scanner in = new Scanner(inStream);
            //PrintWriter out = new PrintWriter(outStream);
            System.out.println("Wait data to receive:");
            String inputData = in.nextLine();
            System.out.println("Received: " + inputData);
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
}
