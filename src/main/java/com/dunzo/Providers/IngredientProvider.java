package com.dunzo.Providers;

import com.dunzo.Storage.IngredientStorage;

public class IngredientProvider {

    public static Boolean isIngredientSupported(String ingredientType) {
        return IngredientStorage.getInstance().getStorage().containsKey(ingredientType);
    }

    public static Boolean isIngredientAvailable(String ingredientType)
    {
        if(!isIngredientSupported(ingredientType))
        {
                // throw exception
                System.out.println("We dont support this ingredient");
        }
        return IngredientStorage.getInstance().getStorage().get(ingredientType)>0;
    }

    public static Integer getIngredientQuantity(String ingredientType)
    {
        if(!isIngredientSupported(ingredientType))
        {
            // throw exception
        }
        return IngredientStorage.getInstance().getStorage().get(ingredientType);
    }

    public static void updateExistingIngredientQuantity(String ingredientType,Integer quantity)
    {
        if(!isIngredientSupported(ingredientType))
        {
            // throw exception

        }
        IngredientStorage.getInstance().getStorage().put(ingredientType,quantity);
    }

    // can add more methods




    
}
