package com.example.phoneBook;
import java.util.*;

class Statistic<T extends AnyCall>{
    private ArrayList<T> lst;
    
    public Statistic(){
        lst = new ArrayList<T>();
    }
    
    public int allTime(){
        int summ = 0;
        for (T x: this.lst){
            summ += x.time;
        }
        return summ;
    };
    public int timeForOneUser(String user){
        int summ = 0;
        for (T x: this.lst){
            if(x.isInvolved(user)) {
                summ += x.time;
            }
        }
        return summ;
    };
    public T getMax(){
        T max;
        if (!lst.isEmpty()){
            max = this.lst.get(0);
            for(T x: this.lst){
                if(x.time > max.time){
                    max = x;
                }
            }
            return max;
        }
        else return null;
    };
    public void addCall(T newCall){
        this.lst.add(newCall);
    }
}

