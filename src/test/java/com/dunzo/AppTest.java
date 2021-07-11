package com.dunzo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ThreadLocalRandom;

import com.dunzo.Providers.BeverageComposerProvider;
import com.dunzo.Providers.composers.BlackTeaBeverageComposer;
import com.dunzo.Providers.composers.GreenTeaComposer;
import com.dunzo.Providers.composers.HotCoffeeComposer;
import com.dunzo.Providers.composers.HotTeaComposer;
import com.dunzo.Providers.composers.Icomposer;
import com.dunzo.Storage.IngredientStorage;
import com.dunzo.coffeeMachine.CoffeeMachine;
import com.dunzo.constants.BeverageConst;
import com.dunzo.constants.IngredientsConst;
import com.dunzo.exceptions.BeverageNotSupportedException;
import com.dunzo.exceptions.IngredientNotSupportedException;

import org.junit.jupiter.api.BeforeAll;

/**
 * Unit test for simple App.
 */
class AppTest {

    CoffeeMachine coffeeMachine;
        int count_n = 4;
        Hashtable<String, Integer> total_items_quantity = new Hashtable<String, Integer>() {
            {
                put(IngredientsConst.HOT_WATER.getIngredientValue(), 5000);
                put(IngredientsConst.HOT_MILK.getIngredientValue(), 5000);
                put(IngredientsConst.GINGER_SYRUP.getIngredientValue(), 1000);
                put(IngredientsConst.SUGAR_SYRUP.getIngredientValue(), 1000);
                put(IngredientsConst.TEA_LEAVES_SYRUP.getIngredientValue(), 1000);
            }
        };


        Hashtable<String, Integer> hotTeaData = new Hashtable<String, Integer>() {
            {
                put(IngredientsConst.HOT_WATER.getIngredientValue(), 200);
                put(IngredientsConst.HOT_MILK.getIngredientValue(), 100);
                put(IngredientsConst.GINGER_SYRUP.getIngredientValue(), 10);
                put(IngredientsConst.SUGAR_SYRUP.getIngredientValue(), 10);
                put(IngredientsConst.TEA_LEAVES_SYRUP.getIngredientValue(), 30);
            }
        };

        Hashtable<String, Integer> hotCoffeeData = new Hashtable<String, Integer>() {
            {
                put(IngredientsConst.HOT_WATER.getIngredientValue(), 100);
                put(IngredientsConst.HOT_MILK.getIngredientValue(), 400);
                put(IngredientsConst.GINGER_SYRUP.getIngredientValue(), 30);
                put(IngredientsConst.SUGAR_SYRUP.getIngredientValue(), 50);
                put(IngredientsConst.TEA_LEAVES_SYRUP.getIngredientValue(), 30);
            }
        };

        Hashtable<String, Integer> blackTeaData = new Hashtable<String, Integer>() {
            {
                put(IngredientsConst.HOT_WATER.getIngredientValue(), 300);
                put(IngredientsConst.GINGER_SYRUP.getIngredientValue(), 30);
                put(IngredientsConst.SUGAR_SYRUP.getIngredientValue(), 50);
                put(IngredientsConst.TEA_LEAVES_SYRUP.getIngredientValue(), 30);
            }
        };

        Hashtable<String, Integer> greenTeaData = new Hashtable<String, Integer>() {
            {
               put(IngredientsConst.HOT_WATER.getIngredientValue(), 100);
               put(IngredientsConst.GINGER_SYRUP.getIngredientValue(), 30);
               put(IngredientsConst.SUGAR_SYRUP.getIngredientValue(), 50);
               put(IngredientsConst.GREEN_MIXTURE.getIngredientValue(), 30);
            }
        };



         
// Test for Storage in Multithreaded Env
    @Test
    void TestStorage() throws InterruptedException {

        Icomposer hotTeaComposer = new HotTeaComposer();
        hotTeaComposer.setRulesForComposer(hotTeaData);


        Icomposer hotCoffeeComposer = new HotCoffeeComposer();
        hotCoffeeComposer.setRulesForComposer(hotCoffeeData);

        Icomposer blackTeaComposer = new BlackTeaBeverageComposer();
        blackTeaComposer.setRulesForComposer(blackTeaData);

        Icomposer greenTeaComposer = new GreenTeaComposer();
        greenTeaComposer.setRulesForComposer(greenTeaData); 

        BeverageComposerProvider.getInstance().setBeverageComposer(BeverageConst.BEVERAGE_HOT_COFFEE.getbeverageValue(), hotCoffeeComposer);
        BeverageComposerProvider.getInstance().setBeverageComposer(BeverageConst.BEVERAGE_HOT_TEA.getbeverageValue(), hotTeaComposer);
        BeverageComposerProvider.getInstance().setBeverageComposer(BeverageConst.BEVERAGE_GREEN_TEA.getbeverageValue(), greenTeaComposer);
        BeverageComposerProvider.getInstance().setBeverageComposer(BeverageConst.BEVERAGE_BLACK_TEA.getbeverageValue(), blackTeaComposer);

        IngredientStorage.getInstance().setStorage(total_items_quantity);

        coffeeMachine = CoffeeMachine.Builder.getNewInstance()
        .setBrandName("ChaiPoint")
        .setNoOfSlots(count_n)
        .build();


coffeeMachine.setSwitchOn(true);
Runnable greenTeaRunnable  = new Runnable() {
    @Override
    public void run() {
        try {
            coffeeMachine.requestBeverage(BeverageConst.BEVERAGE_GREEN_TEA.getbeverageValue());
        } catch (IngredientNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BeverageNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
};

Runnable hotTeaRunnable  = new Runnable() {
    @Override
    public void run() {
        try {
            coffeeMachine.requestBeverage(BeverageConst.BEVERAGE_HOT_TEA.getbeverageValue());
        } catch (IngredientNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BeverageNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
};

Runnable blackTeaRunnable  = new Runnable() {
    @Override
    public void run() {
        try {
            coffeeMachine.requestBeverage(BeverageConst.BEVERAGE_BLACK_TEA.getbeverageValue());
        } catch (IngredientNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BeverageNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }  
};

Runnable hotCoffeeRunnable  = new Runnable() {
    @Override
    public void run() {
        try {
            coffeeMachine.requestBeverage(BeverageConst.BEVERAGE_HOT_COFFEE.getbeverageValue());
        } catch (IngredientNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BeverageNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
};

Runnable arr[] = new Runnable[]{hotTeaRunnable,blackTeaRunnable ,hotCoffeeRunnable};


    Thread hotTeaRunnableThread = new Thread(arr[0]);
    Thread blackTeaRunnableThread = new Thread(arr[1]);
    Thread hotCoffeeRunnableThread = new Thread(arr[2]);
    Thread hotTeaRunnableThread2=new Thread(arr[0]);
    hotTeaRunnableThread .start();
    blackTeaRunnableThread .start();
    hotCoffeeRunnableThread.start();
    hotTeaRunnableThread2.start();
    Thread.sleep(15000);


    assertEquals(4200, total_items_quantity.get(IngredientsConst.HOT_WATER.getIngredientValue()));
    assertEquals(920, total_items_quantity.get(IngredientsConst.GINGER_SYRUP.getIngredientValue()));
    assertEquals(4400, total_items_quantity.get(IngredientsConst.HOT_MILK.getIngredientValue()));
    assertEquals(880, total_items_quantity.get(IngredientsConst.TEA_LEAVES_SYRUP.getIngredientValue()));
        
    }
}
