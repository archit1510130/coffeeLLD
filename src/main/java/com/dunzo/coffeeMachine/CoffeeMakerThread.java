package com.dunzo.coffeeMachine;

import java.util.Date;

public class CoffeeMakerThread implements Runnable {

    private static final int COFFEE_MAKING_TIME = 120 ; // in millisecond
    private int requestId;

    public CoffeeMakerThread(int requestId) {
        this.requestId = requestId;
    }

    public void run() {
        try {
            System.out.println("\nStarted Making Beverage at : " + new Date().getTime()
                    + "for request id : " + requestId);
            Thread.sleep(COFFEE_MAKING_TIME);
            System.out.println("\nFinished Making Beverage at : " + new Date().getTime()
                    + "for request id : " + requestId);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
