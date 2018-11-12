package com.example;
import java.util.*;

class Statistic<T extends AnyCall>{
    private ArrayList<T> lst;
    public int allTime(){
        return 0;
    };
    public int timeForOneUser(){
        return 0;
    };
    public T getMax(){
        T max = lst.get(0);
        for(T x: lst){
            if(x.time > max.time){
                max = x;
            }
        }
        return max;
    };
}

