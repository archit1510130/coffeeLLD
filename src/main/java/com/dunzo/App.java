package com.dunzo;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import com.dunzo.Providers.BeverageComposerProvider;
import com.dunzo.Providers.IngredientProvider;
import com.dunzo.Providers.composers.BlackTeaBeverageComposer;
import com.dunzo.Providers.composers.GreenTeaComposer;
import com.dunzo.Providers.composers.HotCoffeeComposer;
import com.dunzo.Providers.composers.HotTeaComposer;
import com.dunzo.Providers.composers.Icomposer;
import com.dunzo.Storage.IngredientStorage;
import com.dunzo.coffeeMachine.CoffeeMachine;
import com.dunzo.constants.BeverageConst;
import com.dunzo.constants.IngredientsConst;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {

        int count_n = 2;
        HashMap<String, Integer> total_items_quantity = new HashMap<String, Integer>() {
            {
                put(IngredientsConst.HOT_WATER.getIngredientValue(), 5000);
                put(IngredientsConst.HOT_MILK.getIngredientValue(), 5000);
                put(IngredientsConst.GINGER_SYRUP.getIngredientValue(), 1000);
                put(IngredientsConst.SUGAR_SYRUP.getIngredientValue(), 1000);
                put(IngredientsConst.TEA_LEAVES_SYRUP.getIngredientValue(), 1000);
            }
        };


        HashMap<String, Integer> hotTeaData = new HashMap<String, Integer>() {
            {
                put(IngredientsConst.HOT_WATER.getIngredientValue(), 200);
                put(IngredientsConst.HOT_MILK.getIngredientValue(), 100);
                put(IngredientsConst.GINGER_SYRUP.getIngredientValue(), 10);
                put(IngredientsConst.SUGAR_SYRUP.getIngredientValue(), 10);
                put(IngredientsConst.TEA_LEAVES_SYRUP.getIngredientValue(), 30);
            }
        };

        HashMap<String, Integer> hotCoffeeData = new HashMap<String, Integer>() {
            {
                put(IngredientsConst.HOT_WATER.getIngredientValue(), 100);
                put(IngredientsConst.HOT_MILK.getIngredientValue(), 400);
                put(IngredientsConst.GINGER_SYRUP.getIngredientValue(), 30);
                put(IngredientsConst.SUGAR_SYRUP.getIngredientValue(), 50);
                put(IngredientsConst.TEA_LEAVES_SYRUP.getIngredientValue(), 30);
            }
        };

        HashMap<String, Integer> blackTeaData = new HashMap<String, Integer>() {
            {
                put(IngredientsConst.HOT_WATER.getIngredientValue(), 300);
                put(IngredientsConst.GINGER_SYRUP.getIngredientValue(), 30);
                put(IngredientsConst.SUGAR_SYRUP.getIngredientValue(), 50);
                put(IngredientsConst.TEA_LEAVES_SYRUP.getIngredientValue(), 30);
            }
        };

        HashMap<String, Integer> greenTeaData = new HashMap<String, Integer>() {
            {
               put(IngredientsConst.HOT_WATER.getIngredientValue(), 100);
               put(IngredientsConst.GINGER_SYRUP.getIngredientValue(), 30);
               put(IngredientsConst.SUGAR_SYRUP.getIngredientValue(), 50);
               put(IngredientsConst.GREEN_MIXTURE.getIngredientValue(), 30);
            }
        };

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

        CoffeeMachine coffeeMachine = CoffeeMachine.Builder.getNewInstance()
        .setBrandName("ChaiPoint")
        .setNoOfSlots(count_n)
        .build();
coffeeMachine.setSwitchOn(true);

Runnable greenTeaRunnable  = new Runnable() {
    @Override
    public void run() {
        coffeeMachine.requestBeverage(BeverageConst.BEVERAGE_GREEN_TEA.getbeverageValue());
    }
};

Runnable hotTeaRunnable  = new Runnable() {
    @Override
    public void run() {
        coffeeMachine.requestBeverage(BeverageConst.BEVERAGE_HOT_TEA.getbeverageValue());
    }
};

Runnable blackTeaRunnable  = new Runnable() {
    @Override
    public void run() {
        coffeeMachine.requestBeverage(BeverageConst.BEVERAGE_BLACK_TEA.getbeverageValue());
    }
};

Runnable hotCoffeeRunnable  = new Runnable() {
    @Override
    public void run() {
        coffeeMachine.requestBeverage(BeverageConst.BEVERAGE_HOT_COFFEE.getbeverageValue());
    }
};

Runnable arr[] = new Runnable[]{hotTeaRunnable,blackTeaRunnable,blackTeaRunnable};

for(int i=0;i<5;i++) {
    int randomNum = ThreadLocalRandom.current().nextInt(0, 3);
    Thread t = new Thread(arr[randomNum]);
    t.setName("["+ "Thread "+ i +"]");
    t.start();
}
while(true) {
    // For Holding threads execution.
}


    }
}
