package StockExchange;

import java.io.*;

public class Company{
   String name;
   String id;
   float quote;
	
   // This is the constructor of the class Company
   public Company(String compName, String compID, float compQuote){
      this.name = compName;
      this.id = compID;
      this.quote = compQuote;
   }

   public void setName(String compName){
      name = compName;
   }

   public void setID(String compID){
      id =  compID;
   }

   public void setQuote(float compQuote){
      quote = compQuote;
   }

   public String getName(){
      return name;
   }
   
   public String getID(){
      return id;
   }
   
   public float getQuote(){
      return quote;
   }

   public void printCompany(){
      System.out.println("Name: "+ name + "\tID: " + id + "\tQuote: " + quote );
   }
}
