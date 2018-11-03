//import User;
package com.example;
class PhysicalPerson extends User{
    private String mobilePhone;
    public static int index = 0;
    PhysicalPerson(String fio, String phone, String address, String mobilePhone){
        super(fio, phone, address);
        this.mobilePhone = mobilePhone;
    }
    public String getMobilePhone(){
        return this.mobilePhone;
    }
    public void setMobilePhone(String newMobilePhone){
        this.mobilePhone = newMobilePhone;
    }
}
