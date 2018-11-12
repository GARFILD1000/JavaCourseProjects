package com.example.phoneBook;

class Call extends AnyCall{
  private String a;
  private String b;
  public Call(String newA, String newB, int newTime){
      this.a = newA;
      this.b = newB;
      this.time = newTime;
  }
  public void setA(String newA){
      this.a = newA;
  };
  public String getA(){
      return this.a;
  };
  public void setB(String newB){
      this.b = newB;
  };
  public String getB(){
      return this.b;
  };
  public void setTime(int newTime){
      this.time = newTime;
  };
  public int getTime(){
      return this.time;
  };
  public boolean isInvolved(String user){
      if (user.equals(a) || user.equals(b)){ 
          return true;
      }
      else return false;
  }
}
