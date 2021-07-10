package com.dunzo.coffeeMachine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

public class CoffeeMachineThreadExeutor {

    private ExecutorService executor;

    public CoffeeMachineThreadExeutor(int noOfSlots) {
        this.executor = Executors.newFixedThreadPool(noOfSlots);
    }

    public boolean submitTask(int requestId, Semaphore semaphore) {
        Future obj = executor.submit(new CoffeeMakerThread(requestId));
        try {
            obj.get();
            System.out.println("\nLock released successfully by thread : "
                    + Thread.currentThread().getName());
            semaphore.release();
            return true;
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        return false;
    }
}
