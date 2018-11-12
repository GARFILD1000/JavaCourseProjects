package com.example.phoneBook;
import java.util.*;
import java.io.IOException;
import java.io.FileReader;

class PhoneBook{
    static Scanner input;
    static Statistic<Call> statCall;
    static Statistic<Conf> statConf;
    static TreeSet<LegalPerson> legalPersonTree;
    static TreeSet<PhysicalPerson> physicalPersonTree;
    
    static class LegalPersonComparator implements Comparator<LegalPerson>{
        public int compare(LegalPerson paramT1, LegalPerson paramT2) {
            if (paramT1.getID() > paramT2.getID()){
                return 1;
            }
            else if (paramT1.getID() < paramT2.getID())
                return -1;
            else
                return 0;
        }
    }
    
    static class PhysicalPersonComparator implements Comparator<PhysicalPerson> {
        public int compare(PhysicalPerson paramT1, PhysicalPerson paramT2) {
            if (paramT1.getID() > paramT2.getID()){
                return 1;
            }
            else if (paramT1.getID() < paramT2.getID())
                return -1;
            else
                return 0;
        }
    }
    
    public static void main(String[] args){
        input = new Scanner(System.in);
        statCall = new Statistic<Call>();
        statConf = new Statistic<Conf>();
        legalPersonTree = new TreeSet<LegalPerson>(new LegalPersonComparator()); 
        physicalPersonTree = new TreeSet<PhysicalPerson>(new PhysicalPersonComparator());
        legalPersonTree.add(new LegalPerson("Bulgakov Dmitriy Olegovich","3506800","Pushkina-Kolotushkina","1435236257"));
        physicalPersonTree.add(new PhysicalPerson("Teslenok Roman Konstantinovich","3502100","Petrovskaya-Rasumovskaya","88005553535"));
        while(true){
            if (mainCycle() <= 0) break;
        }
    }

    public static String readDatabase(String fileName){
        clearConsole();
        FileReader fr;
        StringBuffer stringBuffer = new StringBuffer("");
        try{
            fr = new FileReader(fileName);
            char[] buffer = new char[3];
            int result = fr.read(buffer, 0, 3);
            while(result > 0){
                stringBuffer = stringBuffer.append(String.valueOf(buffer));
                result = fr.read(buffer, 0, 3);
            }
            System.out.println("Readed from " + fileName + "\n" + stringBuffer);
            fr.close();
        }
        catch(IOException error){
            String exception = error.getMessage();
            System.out.println("Error reading from" + fileName + "\n" + exception);
        }
        return stringBuffer.toString();
    }

    public static void printAllPersons(){
        clearConsole();
        System.out.println("<><><> Legal Persons: <><><>");
        if (legalPersonTree.isEmpty()){
            System.out.println("Empty.");
        }
        for(LegalPerson x: legalPersonTree){
            printPerson(x);
            System.out.println("");
        }
        System.out.println("");
        System.out.println("<><><> Physical Persons: <><><>");
        if (physicalPersonTree.isEmpty()){
            System.out.println("Empty.");
        }
        for(PhysicalPerson x: physicalPersonTree){
            printPerson(x);
            System.out.println("");
        }
        input.nextLine();
    }
    
    public static int deleteAnyPerson(){
        clearConsole();
        System.out.println("Is person to delete Legal or Physical? (input L or P):");
        String buffer = input.nextLine();
        if (buffer.equals("L") || buffer.equals("l") ){
            System.out.print("Enter FIO: ");
            String FIO = input.nextLine();
            while(FIO.isEmpty()){
                FIO = input.nextLine();
            }
            if(legalPersonTree.remove(new LegalPerson(FIO,"","",""))){
                System.out.print("Successfully deleted!");
                LegalPerson.remove();
            }
            else{
                System.out.print("There is no person with same FIO in PhoneBook!");
                input.nextLine();
                return 1;
            }
        }
        if (buffer.equals("P") || buffer.equals("p") ){
            System.out.print("Enter FIO: ");
            String FIO = input.nextLine();
            while(FIO.isEmpty()){
                FIO = input.nextLine();
            }
            if(physicalPersonTree.remove(new PhysicalPerson(FIO,"","",""))){
                System.out.print("Successfully deleted!");
                PhysicalPerson.remove();
            }
            else{
                System.out.print("There is no person with same FIO in PhoneBook!");
                input.nextLine();
                return 1;
            }
        }
        input.nextLine();
        return 0;
    }

    public static int addNewPerson(){
        clearConsole();
        System.out.println("Is new person Legal or Physical? (input L or P):");
        String buffer = input.nextLine();
        if (buffer.equals("L") || buffer.equals("l") ){
            System.out.print("Enter FIO: ");
            String FIO = input.nextLine();
            while(FIO.isEmpty()){
                FIO = input.nextLine();
            }
            System.out.print("Enter Phone Number: ");
            String Phone = input.nextLine();
            while(Phone.isEmpty()){
                Phone = input.nextLine();
            }
            System.out.print("Enter Address: ");
            String Address = input.nextLine();
            while(Address.isEmpty()){
                Address = input.nextLine();
            }
            System.out.print("Enter INN: ");
            String INN = input.nextLine();
            while(INN.isEmpty()){
                INN = input.nextLine();
            }
            LegalPerson newPerson = new LegalPerson(FIO, Phone, Address, INN);
            if (legalPersonTree.add(newPerson)){
                System.out.print("New person added to database!");
            }
            else{
                System.out.print("This person already exists!");
                input.nextLine();
                return 1;
            }
        }
        if (buffer.equals("P") || buffer.equals("p") ){
            System.out.print("Enter FIO: ");
            String FIO = input.nextLine();
            while(FIO.isEmpty()){
                FIO = input.nextLine();
            }
            System.out.print("Enter Phone Number: ");
            String Phone = input.nextLine();
            while(Phone.isEmpty()){
                Phone = input.nextLine();
            }
            System.out.print("Enter Address: ");
            String Address = input.nextLine();
            while(Address.isEmpty()){
                Address = input.nextLine();
            }
            System.out.print("Enter Mobile Phone Number: ");
            String mobilePhone = input.nextLine();
            while(mobilePhone.isEmpty()){
                mobilePhone = input.nextLine();
            }
            PhysicalPerson newPerson = new PhysicalPerson(FIO, Phone, Address, mobilePhone);
            if (physicalPersonTree.add(newPerson)){
                System.out.print("New person added to database!");
            }
            else{
                System.out.print("This person already exists!");
                input.nextLine();
                return 1;
            }
        }
        input.nextLine();
        return 0;
    }

    public static void readAllDatabases(){
        String stringFromDatabase = readDatabase("LegalPersons.csv");
        String[] lines = stringFromDatabase.split("\n");
        LegalPerson.clear();
        legalPersonTree.clear();
        for(String x: lines){
            LegalPerson newLegalPerson = new LegalPerson("","","","");
            newLegalPerson.fromCSV(x);
            legalPersonTree.add(newLegalPerson);
        }
        stringFromDatabase = readDatabase("PhysicalPersons.csv");
        lines = stringFromDatabase.split("\n");
        PhysicalPerson.clear();
        physicalPersonTree.clear();
        for(String x: lines){
            PhysicalPerson newPhysicalPerson = new PhysicalPerson("","","","");
            newPhysicalPerson.fromCSV(x);
            physicalPersonTree.add(newPhysicalPerson);
        }
        input.nextLine();
        return;
    }
    
    private static void addNewCall(){
        clearConsole();
        System.out.println("Is call? (input y or n)");
        if (input.nextLine().equals("y")){
            System.out.print("User A: ");
            String userA = input.nextLine();
            System.out.print("User B: ");
            String userB = input.nextLine();
            System.out.print("Time: ");
            int time = input.nextInt();
            Call newCall = new Call(userA, userB, time); 
            statCall.addCall(newCall);
        }
        else{
            System.out.println("Is conference? (input y or n)");
            if (input.nextLine().equals("y")){
                ArrayList<String> users = new ArrayList<String>();
                System.out.print("User " + users.size() + " : ");
                String name = input.nextLine();
                while(name.length() > 0){
                    users.add(name);
                    System.out.print("User " + users.size() + " : ");
                    name = input.nextLine();
                }
                if (users.size() > 2){
                    
                    String[] stringUsers = users.toArray(new String[0]);
                    System.out.print("Time: ");
                    int time = input.nextInt();
                    Conf newConf = new Conf(stringUsers, time);
                    statConf.addCall(newConf);
                }
                else{
                    System.out.print("It's not conference! Try to add into calls.");
                }
            }
        }
        input.nextLine();
    }
    
    private static void printStatistic(){
        clearConsole();
        System.out.println("<><><> Calls: <><><>");
        System.out.println("All time: " + statCall.allTime() + " seconds");
        Call maxCall = statCall.getMax();
        if (maxCall != null){
            System.out.println("The longest call: " + maxCall.getTime() + " seconds" + " ( " + maxCall.getA() + " -> " + maxCall.getB() + " )");
        }
        System.out.println("<><><> Conferences: <><><>");
        System.out.println("All time: " + statConf.allTime() + " seconds");
        Conf maxConf = statConf.getMax();    
        if (maxConf != null){
            System.out.print("The longest conference: " + maxConf.getTime() + " seconds ( Members: ");
            for (String x: maxConf.getUsers()){
                System.out.print(x + ", ");
            }
            System.out.println(" )");
        }
        input.nextLine();
    } 
    
    private static void printPerson(LegalPerson person){
        System.out.println("ID: " + person.getID() + "\nName: " + person.getFio() + "\nPhone: " + person.getPhone() + "\nAddress: " + person.getAddress() + "\nINN: " +
                person.getINN());
    }
    
    private static void printPerson(PhysicalPerson person){
        System.out.println("ID: " + person.getID() + "\nName: " + person.getFio() + "\nPhone: " + person.getPhone() + "\nAddress: " + person.getAddress() + 
            "\nMobileNumber: " + person.getMobilePhone());
    }
    
    public static void clearConsole(){
        try{
            new ProcessBuilder("/bin/bash","-c","clear").inheritIO().start().waitFor();
        }
        catch(InterruptedException | IOException e){
            e.printStackTrace();
        }
    }
    
    public static int mainCycle(){
        clearConsole();
        System.out.println("<><><> Phone Book Menu <><><>");
        System.out.println("1. Print all persons\n2. Add new person\n3. Delete any person\n4. Read Database\n5. Print Statistic\n6. Add new Call\n0. Exit program");
        System.out.print("Enter menu item number: ");
        String buffer = input.nextLine();
        switch(Integer.parseInt(buffer)){
        case 1: 
            printAllPersons();
        break; 
        case 2:
            addNewPerson();
        break;
        case 3:   
            deleteAnyPerson();
        break;
        case 4:
            readAllDatabases();
        break;
        case 5:
            printStatistic();
        break;
        case 6:
            addNewCall();
         break;
        case 0:
            return 0;
        default:
            return -1;
        }
        return 5; 
    } 
    
}

