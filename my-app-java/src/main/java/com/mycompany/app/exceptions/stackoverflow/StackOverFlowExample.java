package com.mycompany.app.exceptions.stackoverflow;

public class StackOverFlowExample {
    static int i = 0;

    // Method to print numbers
    public static int printNumber(int x){
        i = i + 2;
        System.out.println(i);
        // Terminating condition
//        if (i == 10)
//            return i; // solve stackoverflowerror
        return i + printNumber(i + 2);
    }

    public static void main(String[] args) {
        StackOverFlowExample.printNumber(i);
    }
}
