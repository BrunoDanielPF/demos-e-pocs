package com.mycompany.app.exceptions;

public class IllegalArgumentExceptionExample {

    // Main driver method
    public static void main(String[] args)
    {
        // Creating a new thread
        Thread t = new Thread();

        // valid in thread
        t.setPriority(10);

        // invalid(IllegalArgumentException(IAE))
        t.setPriority(100);
    }
}
