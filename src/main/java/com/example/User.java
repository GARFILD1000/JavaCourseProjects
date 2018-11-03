package com.example;

abstract class User{
    protected User(String newFio, String newPhone, String newAddress){
        this.fio = newFio;
        this.phone = newPhone;
        this.address = newAddress;
    }
    protected String fio;
    protected String phone;
    protected String address;

    public String getFio(){
        return this.fio;
    }
    public void setFio(String newFio){
        this.fio = newFio;
    }

    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String newPhone){
        this.phone = newPhone;
    }

    public String getAddress(){
        return this.address;
    }
    public void setAddress(String newAddress){
        this.address = newAddress;
    }
}

