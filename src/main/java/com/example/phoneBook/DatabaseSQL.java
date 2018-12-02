package com.example.phoneBook;
import java.util.*;
import java.io.*;
import java.sql.*;
import java.net.URL;
import java.lang.Exception; 

class DatabaseSQL{
    private String databaseName;
    private String hostName;
    private String portNumber;
    private String driverName;
    private String host;
    private final static String psw = "";
    private final static String usr = "root";
    public boolean connected;
    private Connection con;
    private Statement st;
    
    public DatabaseSQL(String newDatabaseName){
        driverName = "jdbc:mysql";
        hostName = "localhost";
        portNumber = "3306";
        databaseName = newDatabaseName;
        con = null;
        st = null;
    }
    
    public void connect(){ 
        String URL = driverName + "://" + hostName + ":" + portNumber + "/" + databaseName;  
        try{
            con = DriverManager.getConnection(URL, usr, psw);
            st = con.createStatement();
            if (con != null){
                this.connected = true;
            }
        }
        catch(SQLException e){
           e.printStackTrace();
        }
    }
    
    public void close(){
        this.connected = false;
        try{
            con.close();
        }
        catch(SQLException e){
           e.printStackTrace();
        }
    }
    
    public void executeQuery(String query){
        try{
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                String result = rs.getString("fio");
                System.out.println(result);
            }
        }
        catch(SQLException e){
           e.printStackTrace();
        }
    }




}


