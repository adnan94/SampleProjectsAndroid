/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practiceprograms;

import java.util.ArrayList;

/**
 *
 * @author adnanmac
 */
public class PracticePrograms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PracticePrograms p=new PracticePrograms();
        p.primeNumbers(1); 
    p.evenNumbers(9);
    p.printFactorials(4);
    p.palendrome("Loly");
    p.patternsRightAngle();
        p.patternsSquare();
        p.patternsTriAngleRevers();

    }
    public void primeNumbers(int number){
          ArrayList arraylist=new ArrayList<>();
          int counter=0; 	  
          for(int num =number; num>=1; num--)
	  {
          if(number%num==0){
 		counter = counter + 1;
	  }}
	  if (counter ==2 || number==1){
	  System.out.println(number+" is a prime number");
          }}
   
    public void evenNumbers(int number){
       if(number%2==0){
       	  System.out.println(number+" is a even");
       }else{
       	  System.out.println(number+" is a odd number");
}
    
    }
    public void printFactorials(int number){
   int factorial=1;
        for(int i=number;i>=1;i--){
            factorial=factorial*i;
          }
        System.out.println("Factorial of "+number+" is "+ factorial);
    }
    public void sorting(){
    
    }

    public void palendrome(String str){
    String str2="";
    for(int i=str.length()-1;i>=0;i--){
    str2+=str.charAt(i);
    }
    if(str.equalsIgnoreCase(str2)){
    System.out.println("It is palendrome");
    }else{
    System.out.println("It is not palendrome");
    }
    }

    public void patternsRightAngle(){

        for(int i=0;i<6;i++){
    for(int j=0;j<i;j++){
        System.out.print("*");    
    }
    System.out.println("");
    }
    }
    
public void patternsSquare(){
          System.out.print("\n \n");    

    for(int i=0;i<4;i++){
    for(int j=0;j<8;j++){
        System.out.print("*");    
    }
    System.out.println("");
    }
    }

public void patternsTriAngleRevers(){
        System.out.print("\n \n");    

        for(int i=6;i>0;i--){

    for(int j=i;j>0;j--){
                    System.out.print(" ");    

        System.out.print("*");    
    }
    System.out.println("");
    }
    }
}
