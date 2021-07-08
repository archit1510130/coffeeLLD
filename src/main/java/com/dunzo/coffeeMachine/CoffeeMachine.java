package com.dunzo.coffeeMachine;

import java.util.Map;
import java.util.concurrent.Semaphore;

import com.dunzo.Providers.BeverageComposerProvider;
import com.dunzo.Providers.IngredientProvider;
import com.dunzo.Providers.composers.Icomposer;
import com.dunzo.beverages.Ibeverage;

public class CoffeeMachine {

    private MachineDetails machineDetails;
    private Boolean isSwitchOn;
    private IngredientProvider ingredientProvider;
    private BeverageComposerProvider beverageComposerProvider;
    private Semaphore semaphore;


    public Ibeverage requestBeverage(String beverageType)
    {
        if(!isSwitchOn)
        {
            // throw exception here
        }
        if(1==1)
        {
            // if beverage does not suport
        }
        boolean isAvailableSlot=semaphore.tryAcquire();
        if(!isAvailableSlot)
        {
            // no available slot there // throw
            
        }
        // print thread number
        Ibeverage beverage=null;
        synchronized(ingredientProvider)
        {
            Icomposer composer=beverageComposerProvider.getBeverageComposer(beverageType);
           Map<String ,Integer> composition= composer.getRulesForComposer();
           for (Map.Entry<String, Integer> ingredientQuantity : composition.entrySet()) {
            if (!ingredientProvider.isIngredientSupported(ingredientQuantity.getKey())) {
                System.out.println("\nMachine Does not support ingredients for particular beverage: " + beverageType);
                semaphore.release();
                return null;// throw
            }
            if (ingredientProvider.getIngredientQuantity(ingredientQuantity.getKey())
                    < ingredientQuantity.getValue()) {
                System.out.println("\nMachine Does not have sufficient ingredients for particular beverage: " + beverageType);
                semaphore.release();
                return null;// throw
            }


        }

        beverage = null;//BeverageFactory.getBeverage(beverageType);
        updateIngredientStore(beverage);

        }

        startMakingBeverage(beverage);
        
        return null;

    }

    private void startMakingBeverage(Ibeverage beverage)
    {
        // each thread will call here and do process to make beverage
    }


    private synchronized void updateIngredientStore(Ibeverage beverage) {
        Icomposer composer = beverageComposerProvider.getBeverageComposer(beverage.getType());
        Map<String, Integer> composition = composer.getRulesForComposer();

        for (Map.Entry<String, Integer> ingredientQuantity : composition.entrySet()) {
            int amount = ingredientProvider.getIngredientQuantity(ingredientQuantity.getKey());
            amount -= ingredientQuantity.getValue();
            ingredientProvider.updateExistingIngredientQuantity(ingredientQuantity.getKey(), amount);
        }
    }
    
}
