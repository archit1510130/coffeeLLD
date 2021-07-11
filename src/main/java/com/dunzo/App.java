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
import com.dunzo.exceptions.BeverageNotSupportedException;
import com.dunzo.exceptions.IngredientNotSupportedException;

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

   }
}
