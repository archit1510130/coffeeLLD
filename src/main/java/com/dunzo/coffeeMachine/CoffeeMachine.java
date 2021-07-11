package com.dunzo.coffeeMachine;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.Semaphore;

import com.dunzo.Providers.BeverageComposerProvider;
import com.dunzo.Providers.IngredientProvider;
import com.dunzo.Providers.composers.Icomposer;
import com.dunzo.beverages.BeverageFactory;
import com.dunzo.beverages.Ibeverage;
import com.dunzo.exceptions.BeverageNotSupportedException;
import com.dunzo.exceptions.IngredientNotSupportedException;

public class CoffeeMachine {

    private MachineDetails machineDetails;
    private Boolean isSwitchOn;
    private Semaphore semaphore;
    private CoffeeMachineThreadExeutor coffeeMachineThreadExeutor;

    // Used Builder Pattern for creating coffee machine
    public CoffeeMachine(Builder builder) {
        machineDetails = new MachineDetails();
        machineDetails.setBrandName(builder.brandName);
        machineDetails.setNoOfSlots(builder.noOfSlots);
        this.isSwitchOn = builder.isSwitchOn;
        coffeeMachineThreadExeutor = new CoffeeMachineThreadExeutor(builder.noOfSlots);
        this.semaphore = new Semaphore(builder.noOfSlots);
    }
    public static class Builder {
        private String brandName;
        private int noOfSlots;
        private Boolean isSwitchOn;

        private Builder() { }

        public static Builder getNewInstance() {
            return new Builder();
        }

        public Builder setBrandName(String brandName) {
            this.brandName = brandName;
            return this;
        }

        public Builder setNoOfSlots(int noOfSlots) {
            this.noOfSlots = noOfSlots;
            return this;
        }

        public Builder setSwitchOn(Boolean switchOn) {
            isSwitchOn = switchOn;
            return this;
        }

        public CoffeeMachine build() {
            return new CoffeeMachine(this);
        }
    }

    public Ibeverage requestBeverage(String beverageType) throws IngredientNotSupportedException, BeverageNotSupportedException
    {
        if(!isSwitchOn)
        {
            System.out.println("Switch is Off");
        }
        if(BeverageFactory.getBeverage(beverageType)==null)
        {
            throw new BeverageNotSupportedException("Beverage Not Supported");
            // if beverage does not suport
        }
        boolean isAvailableSlot=semaphore.tryAcquire();
        if(!isAvailableSlot)
        {
           System.out.println("No Slot now");
            
        }
        // print thread number
        checkIngredientAvaillity(beverageType);
        Ibeverage beverage=BeverageFactory.getBeverage(beverageType);// beveragefactory would provide
        updateIngredientStore(beverage);

        startMakingBeverage(beverage);// lets each thread do the action for making 
        
        return beverage;

    }


    private synchronized void checkIngredientAvaillity(String beverageType) throws IngredientNotSupportedException
    {
        Icomposer composer=BeverageComposerProvider.getInstance().getBeverageComposer(beverageType);
        Map<String ,Integer> composition= composer.getRulesForComposer();
           for (Map.Entry<String, Integer> ingredientQuantity : composition.entrySet()) {
            if (!IngredientProvider.isIngredientSupported(ingredientQuantity.getKey())) {
                System.out.println("\nMachine Does not support ingredients for particular beverage: " + beverageType);
                semaphore.release();
               
            }
            if (IngredientProvider.getIngredientQuantity(ingredientQuantity.getKey())
                    < ingredientQuantity.getValue()) {
                System.out.println("\nMachine Does not have sufficient ingredients for particular beverage: " + beverageType);
                semaphore.release();
            }
        }

    }

    private void startMakingBeverage(Ibeverage beverage)
    {
        Random rand = new Random();
        int requestId = Math.abs(rand.nextInt());
        boolean beverageProcessed = coffeeMachineThreadExeutor.submitTask(requestId, semaphore);
        System.out.println("Process Execution finished for task : " + Thread.currentThread().getName());
        // each thread will call here and do process to make beverage
    }


    private synchronized void updateIngredientStore(Ibeverage beverage) throws IngredientNotSupportedException {
        Icomposer composer = BeverageComposerProvider.getInstance().getBeverageComposer(beverage.getType());
        Map<String, Integer> composition = composer.getRulesForComposer();

        for (Map.Entry<String, Integer> ingredientQuantity : composition.entrySet()) {
            int amount =IngredientProvider.getIngredientQuantity(ingredientQuantity.getKey());
            amount -= ingredientQuantity.getValue();
            System.out.println(ingredientQuantity.getKey()+" "+ingredientQuantity.getValue()+" "+amount);
            IngredientProvider.updateExistingIngredientQuantity(ingredientQuantity.getKey(), amount);
        }
    }

    public MachineDetails getMachineDetails() {
        return machineDetails;
    }

    public void setMachineDetails(MachineDetails machineInfo) {
        this.machineDetails = machineInfo;
    }

    public Boolean getSwitchOn() {
        return isSwitchOn;
    }

    public void setSwitchOn(Boolean switchOn) {
        isSwitchOn = switchOn;
    }
    
}
