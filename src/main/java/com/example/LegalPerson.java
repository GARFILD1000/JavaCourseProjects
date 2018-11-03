//import User;
package com.example;
class LegalPerson extends User{
    private String INN;
    public static int index = 0;
    LegalPerson(String fio, String phone, String address, String INN){
        super(fio, phone, address);
        this.INN = INN;
    }
    public String getINN(){
        return this.INN;
    }
    public void setINN(String newINN){
        this.INN = newINN;
    }

}
