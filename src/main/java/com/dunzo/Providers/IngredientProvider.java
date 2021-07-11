package com.dunzo.Providers;

import com.dunzo.Storage.IngredientStorage;
import com.dunzo.exceptions.IngredientNotSupportedException;

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

    public static Integer getIngredientQuantity(String ingredientType) throws IngredientNotSupportedException
    {
        if(!isIngredientSupported(ingredientType))
        {
            throw new IngredientNotSupportedException("Ingredient Is not Supported");
        }
        return IngredientStorage.getInstance().getStorage().get(ingredientType);
    }

    public static void updateExistingIngredientQuantity(String ingredientType,Integer quantity) throws IngredientNotSupportedException
    {
        if(!isIngredientSupported(ingredientType))
        {
            throw new IngredientNotSupportedException("Ingredient Is not Supported");
        }
        IngredientStorage.getInstance().getStorage().put(ingredientType,quantity);
    }

    // can add more methods




    
}
