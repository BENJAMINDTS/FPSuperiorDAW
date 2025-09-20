package com.mycompany.break_and_continue;

public class Break_And_Continue {

    public static void main(String[] args) {
        int i;
        for (i = 1; i <= 50; i++) {
            if (i%3==0){
            continue;
            }
            else{
                System.out.println(i);   
            }
            if (i>=40) {
                System.out.println("Se uso un break");
                break;    
            }
            
        }
    }
}
