package com.example;

class Conf extends AnyCall{
  private String[] users;
  public void setUsers(String[] newUsers){
      this.users = newUsers;
  };
  public String[] getUsers(){
      return this.users;
  };
  public void setTime(int newTime){
      this.time = newTime;
  };
  public int getTime(){
      return this.time;
  };
}
