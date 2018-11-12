package com.example.phoneBook;

class Conf extends AnyCall{
  private String[] users;
  public Conf(String[] newUsers, int newTime){
      this.users = newUsers;
      this.time = newTime;
  }
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
  public boolean isInvolved(String user){
      for(String x: users){
          if (user.equals(x)){
              return true;
          }
      }
      return false;
  }
}
