package com.example.checkTiming;
import java.util.Scanner;
import java.util.*;
import java.lang.Math;
import com.example.phoneBook.LegalPerson;


class CheckTiming{
    static class MyComparator implements Comparator<LegalPerson> {
        public int compare(LegalPerson paramT1, LegalPerson paramT2) {
            if (paramT1.getFio().compareTo(paramT2.getFio()) > 0)
                return -1;
            else if (paramT1.getFio().compareTo(paramT2.getFio()) < 0)
                return 1;
            else
                return 0;
        }
    }


    public static void main(String[] args) {
        Random rand = new Random(System.currentTimeMillis());
        int numberOfItems = (int)Math.pow(10,4);
        long timeConsumedMillis;
        long start = System.currentTimeMillis();
        ArrayList<LegalPerson> array = new ArrayList<LegalPerson>(); 
        for (int i = 0; i < numberOfItems; i++){
            array.add(new LegalPerson(Double.toString(Math.random()*9999),Double.toString(Math.random()*9999),Double.toString(Math.random()*9999),Double.toString(Math.random()*9999)));
        }
        long finish = System.currentTimeMillis();
        timeConsumedMillis = finish - start;
        System.out.println("Time, consumed for creating " + numberOfItems + " items of ArrayList: " + timeConsumedMillis + " milliseconds");


        start = System.currentTimeMillis();
        LinkedList<LegalPerson> list = new LinkedList<LegalPerson>(); 
        for (int i = 0; i < numberOfItems; i++){
            list.add(new LegalPerson(Double.toString(Math.random()*9999),Double.toString(Math.random()*9999),Double.toString(Math.random()*9999),Double.toString(Math.random()*9999)));
        }
        finish = System.currentTimeMillis();
        timeConsumedMillis = finish - start;
        System.out.println("Time, consumed for creating " + numberOfItems + " items of LinkedList: " + timeConsumedMillis + " milliseconds");


        start = System.currentTimeMillis();
        TreeSet tree = new TreeSet(new MyComparator()); 
        for (int i = 0; i < numberOfItems; i++){
            tree.add(new LegalPerson(Double.toString(Math.random()*9999),Double.toString(Math.random()*9999),Double.toString(Math.random()*9999),Double.toString(Math.random()*9999)));
        }
        finish = System.currentTimeMillis();
        timeConsumedMillis = finish - start;
        System.out.println("Time, consumed for creating " + numberOfItems + " items of TreeSet: " + timeConsumedMillis + " milliseconds");

        ArrayList<LegalPerson> elementsForSearchInTree = new ArrayList<LegalPerson>(numberOfItems);
        for(int i = 0; i < numberOfItems; i++){
            elementsForSearchInTree.add(array.get(rand.nextInt(numberOfItems)));
        }

        start = System.currentTimeMillis();
        for (int i = 0; i < numberOfItems; i++){
            array.contains(elementsForSearchInTree.get(i));
        }
        finish = System.currentTimeMillis();
        timeConsumedMillis = finish - start;
        System.out.println("Time, consumed for getting " + numberOfItems + " random items of ArrayList: " + timeConsumedMillis + " milliseconds");

        start = System.currentTimeMillis();
        for (int i = 0; i < numberOfItems; i++){
            list.contains(elementsForSearchInTree.get(i));
        }
        finish = System.currentTimeMillis();
        timeConsumedMillis = finish - start;
        System.out.println("Time, consumed for getting " + numberOfItems + " random items of LinkedList: " + timeConsumedMillis + " milliseconds");


        start = System.currentTimeMillis();
        for (int i = 0; i < numberOfItems; i++){
            tree.contains(elementsForSearchInTree.get(i));
        }
        finish = System.currentTimeMillis();
        timeConsumedMillis = finish - start;
        System.out.println("Time, consumed for getting " + numberOfItems + " random items of TreeSet: " + timeConsumedMillis + " milliseconds");



        for(int i = numberOfItems - 1; i >= 0; i--){
            elementsForSearchInTree.remove(i);
        }
        for(int i = 0; i < numberOfItems; i++){
            elementsForSearchInTree.add(array.get(i));
        }

        start = System.currentTimeMillis();
        for(int i = numberOfItems - 1; i >= 0; i--){
            array.remove(i);
        }
        finish = System.currentTimeMillis();
        timeConsumedMillis = finish - start;
        System.out.println("Time, consumed for deliting all of " + numberOfItems + " items of ArrayList: " + timeConsumedMillis + " milliseconds");

        start = System.currentTimeMillis();
        for(int i = numberOfItems - 1; i >= 0; i--){
            list.remove(i);
        }
        finish = System.currentTimeMillis();
        timeConsumedMillis = finish - start;
        System.out.println("Time, consumed for deliting all of " + numberOfItems + " items of LinkedList: " + timeConsumedMillis + " milliseconds");

        start = System.currentTimeMillis();
        for (int i = 0; i < numberOfItems; i++){
            tree.remove(elementsForSearchInTree.get(i));
        }
        finish = System.currentTimeMillis();
        timeConsumedMillis = finish - start;
        System.out.println("Time, consumed for deliting all of " + numberOfItems + " items of TreeSet: " + timeConsumedMillis + " milliseconds");

        
       
    }
}
