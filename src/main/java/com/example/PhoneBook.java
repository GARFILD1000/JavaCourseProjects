package com.example;
import java.util.*;
import java.io.IOException;

//import PhysicalPerson;
//import LegalPerson;

class PhoneBook{
    public static void main(String[] args){
        ArrayList<LegalPerson> array = new ArrayList<LegalPerson>();     //класс для массива
        TreeSet<LegalPerson> tree = new TreeSet<LegalPerson>();         //древовидная структура
        LinkedList<LegalPerson> list = new LinkedList<LegalPerson>();   //класс для списка
        list.add(new LegalPerson("Bulgakov Dmitriy Olegovich","3506800","Pushkina-Kilitushkina","1435236257"));
        printPerson(list.get(0));
        while(true){
            if (mainCycle(list) <= 0) break;
        }
    }
    public static int mainCycle(LinkedList<LegalPerson> list){
        try{
            clearConsole();
        }
        catch(InterruptedException | IOException e){
            e.printStackTrace();
        }
        System.out.println("<><><> Phone Book Menu <><><>");
        System.out.println("1. Show all users\n2. Add new user\n3. Delete any user\n0. Exit program");
        System.out.print("Enter menu item number: ");
        Scanner input = new Scanner(System.in);
        int buffer = input.nextInt();
        switch(buffer){
        case 1: 
            System.out.println("Legal Persons:");
            for(LegalPerson x: list){
                printPerson(x);
            }
        break; 
        case 2:
            System.out.print("Status of person (Legal/Physical): ");
            buffer = input.nextInt();
            
            
        break;
        case 3:   
        break;
        case 0:
            return 0;
        default:
            return -1;
        }
        return 5; 
    } 
    private static void printPerson(LegalPerson person){
        System.out.println("Name: " + person.getFio() + "\nPhone: " + person.getPhone() + "\nAddress: " + person.getAddress() + "\nINN: " +
                person.getINN());
    }
    private static void printPerson(PhysicalPerson person){
        System.out.println("Name: " + person.getFio() + "\nPhone: " + person.getPhone() + "\nAddress: " + person.getAddress() + 
            "\nMobileNumber: " + person.getMobilePhone());
    }
    public static void clearConsole() throws IOException, InterruptedException {
        new ProcessBuilder("/bin/bash","-c","clear").inheritIO().start().waitFor();
    }
}

/*
try{
    add
}
catch(Exeption e){
    e.getMessage();
}
*/
