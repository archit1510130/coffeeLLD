package com.dunzo.Providers;

import com.dunzo.Storage.IngredientStorage;

public class IngredientProvider {

    public Boolean isIngredientSupported(String ingredientType) {
        return IngredientStorage.getInstance().getStorage().containsKey(ingredientType);
    }

    public Boolean isIngredientAvailable(String ingredientType)
    {
        if(!this.isIngredientSupported(ingredientType))
        {
                // throw exception
        }
        return IngredientStorage.getInstance().getStorage().get(ingredientType)>0;
    }

    public Integer getIngredientQuantity(String ingredientType)
    {
        if(!this.isIngredientSupported(ingredientType))
        {
            // throw exception
        }
        return IngredientStorage.getInstance().getStorage().get(ingredientType);
    }

    public void updateExistingIngredientQuantity(String ingredientType,Integer quantity)
    {
        if(!this.isIngredientSupported(ingredientType))
        {
            // throw exception
        }
        IngredientStorage.getInstance().getStorage().put(ingredientType,quantity);
    }

    // can add more methods




    
}
