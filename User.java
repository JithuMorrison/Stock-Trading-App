import java.util.*;
class User{
  private String username;
  private String password;
  private String email;
  private String phoneNumber;
  private String firstName;
  private String lastName;
  private String streetAddress;
  private String city;
  private String state;
  private String zipCode;
  private String creditCardNumber;
  private String creditCardExpirationDate;
  private String creditCardSecurityCode;
  private String creditCardType;
  private String creditCardNameOnCard;
  private String creditCardAddress;
  private String userid;
  public String getUserId(){
    return userid;
  }
  public String getUsername(){
    return username;
  }
  public void setUsername(String username){
    this.username = username;
  }
  public String getPassword(){
    return password;
  }
  public void setPassword(String password){
    this.password = password;
  }
  public void setEmail(String email){
    this.email = email;
  }
}
class Registereduser extends User{
  private double balance;
  public void setBalance(double balanc){
    this.balance=balanc;
  }
  public double getBalance(){
    return balance;
  }
}
class Authentication{
  HashMap users=new HashMap<String,User>();
  public boolean authenticate(String username, String password){
    if(username.equals("admin") && password.equals("admin")){
      return true;
    }
    else{
      return false;
    }
  }
}
class Portfolio{
  int portfolioid;
  Registereduser user;
  Stocks mystocks;
  StockPrice stockprice;
  Stocks buyprice;
  Stocks addrt;
  
  public Portfolio(int pfid,Registereduser rgu,Stocks stk,StockPrice adk,Stocks bp,Stocks adde){
    
    this.portfolioid=pfid;
    this.user=rgu;
    this.mystocks=stk;
    this.stockprice=adk;
    this.buyprice=bp;
    this.addrt=adde;
    
  }
  
  public void setPortfolioId(int portfolioid){
    this.portfolioid=portfolioid;
  }
  public int getPortfolioId(){
    return portfolioid;
  }
  public void setUser(Registereduser user){
    this.user=user;
  }
  public Registereduser getUser(){
    return user;
  }
  public void setMystocks(Stocks mystocks){
    this.mystocks=mystocks;
  }
  public Stocks getMystocks(){
    return mystocks;
  }
}