import java.util.*;
import java.io.*;
import java.net.*;
//import NetworkServerRun;


class NetworkServer{
    public NetworkServer(){
    
    }
    public int count = 0;
    
    public void startServer(){
        ServerSocket serverSocket = null;
        Socket client = null;
        try{
            try{
                
                serverSocket = new ServerSocket(4004);
                System.out.println("Server started!");
                while(true){
                    count++;
                    if (count%2 == 0){
                    client = serverSocket.accept();
                    System.out.println("Client connect accepted!");
                    new Thread( new NetworkServerRun(client, this)).start();
                    }
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
    
    public String readHtml(String fileName){
        String readedString = new String();
        try{
            RandomAccessFile safetyFilereader = new RandomAccessFile(fileName, "r");
            byte[] bytesArray = new byte[(int)safetyFilereader.length()];
            safetyFilereader.readFully(bytesArray);
            readedString = new String(bytesArray);
        }
        catch(FileNotFoundException ex){
            System.err.println("File not found: "+ex);
        }
        finally{
            return readedString;
        }
        
    }
}
