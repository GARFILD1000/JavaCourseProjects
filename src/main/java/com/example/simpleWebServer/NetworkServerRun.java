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
                System.out.printf("\n----------Server created new thread for client.---------\n", iterations);
                outStream = client.getOutputStream();
                inStream = client.getInputStream();
                out = new PrintWriter(outStream);
                fromClient = new Scanner(inStream);
                //out.println(data);
                //out.flush();
                LinkedList<String> stringsFromClient = readInputHeaders();
                for (String x : stringsFromClient){
                    System.out.println(x);
                }
                
                String refer = parseGetRefer(stringsFromClient);
                System.out.println("Refer to file: <"+refer+">");
                if (!refer.isEmpty()){
                    int positionOfQuery = refer.indexOf("?");
                    if (positionOfQuery >= 0){
                        String expression = new String();
                        String queryString = URLDecoder.decode(refer.substring(positionOfQuery+1));
                        String expressionPrefix = new String("expression=");
                        String result = new String();
                        LinkedList<String> queries = parseHttpQuery(queryString);
                        for (String x: queries){
                            if (x.indexOf(expressionPrefix) >= 0){
                                expression = x.substring(x.indexOf(expressionPrefix) + expressionPrefix.length());
                            }
                        } 
                        if (!expression.isEmpty()){
                            System.out.println("Expression: "+expression);
                            result = Calculator.calculateExpression(expression);
                        }
                        StringBuilder htmlBody = new StringBuilder(server.readHtml(refer.substring(0, positionOfQuery)));
                        String resultPrefix = new String("Result: ");
                        System.out.println("Result: "+result);
                        htmlBody.insert(htmlBody.indexOf(resultPrefix)+resultPrefix.length(), result);
                        writeResponse(htmlBody.toString());
                    }
                    else if(!refer.equals("favicon.ico")){
                        String htmlBody = server.readHtml(refer);
                        if (!htmlBody.isEmpty()){
                            writeResponse(htmlBody);
                        }
                        else{
                            writeResponse("<html><head><title>Server</title></head><body><p>This page doesn't exists</p><a href=\"index.html\">Go To Main</a></body></html>");
                        }
                    }
                }
                else{
                    writeResponse("<html><head><title>Server</title></head><body><p>This page doesn't exists</p><a href=\"index.html\">Go To Main</a></body></html>");
                }
            }
            finally{
                outStream.close();
                inStream.close();
                client.close();
            }
        }
        catch(IOException ex){
            System.err.println(ex);
        }
    }
    
    public String parseGetRefer(LinkedList<String> fullHeader){
        String refer = new String();
        if (!fullHeader.isEmpty() && fullHeader.getFirst().split(" ")[0].equals("GET")){
            System.out.println("GET query catched");
            for (String x : fullHeader){
                if (x.indexOf("GET ") >= 0 ){
                    refer = x.split(" ")[1];
                    refer = refer.substring(refer.indexOf("/")+1);
                    break;
                }
            }
        }
        return refer;
    }
    
        public String parsePostRefer(LinkedList<String> fullHeader){
        String refer = new String();
        String httPrefix = new String("http://");
        if (!fullHeader.isEmpty() && fullHeader.getFirst().split(" ")[0].equals("POST")){
            System.out.println("POST query catched");
            for (String x : fullHeader){
                if (x.indexOf("Referer: ") >= 0 ){
                    refer = x.substring(x.indexOf(httPrefix) + httPrefix.length());
                    refer = refer.substring(refer.indexOf("/")+1);
                    break;
                }
            }
        }
        return refer;
    }
    
    public LinkedList<String> parseHttpQuery(String query){
        LinkedList<String> queries = new LinkedList<String>();
        String[] array = query.split("&");
        for (String x : array){
            queries.add(x);
        }
        return queries;
    }
    
    public LinkedList<String> readInputHeaders(){
        LinkedList<String> fullHeader = new LinkedList<String>();
        while(true) {
            if (fromClient.hasNextLine()){
            String header = fromClient.nextLine();
            //System.out.println(header);
            fullHeader.add(header);
            if(header == null || header.trim().length() == 0) {
                break;
            }
            }
            else {
                break;
            }
        }
        return fullHeader;
    }
    
    private void writeResponse(String htmlString){
        try{
            String response = "HTTP/1.1 200 OK\r\n" +
                    "Server: CalculatorServer\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: " + htmlString.length() + "\r\n" +
                    "Connection: close\r\n\r\n";
                    

            String result = response + htmlString;
            //System.out.println("\nSending: \n"+result);
            outStream.write(result.getBytes());
            outStream.flush();
            //out.println(result);
            //out.flush();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
