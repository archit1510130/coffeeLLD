package com.dunzo.constants;

public enum IngredientsConst {

    HOT_WATER ("Hot Water") ,
    HOT_MILK ("Hot Milk") ,
    GINGER_SYRUP ("Ginger Syrup") ,
    SUGAR_SYRUP ("Sugar Syrup") ,
    TEA_LEAVES_SYRUP ("Tea Leaves") ,
    GREEN_MIXTURE ("Green Mixture") ,
    ELAICHI_SYRYP("Elaichi Syrup"),
    COFFEE_SYRUP("Coffee Syrup");

    private String ingredientValue;

    public String getIngredientValue()
    {
        return this.ingredientValue;
    }

    private IngredientsConst(String ingredientValue)
    {
        this.ingredientValue = ingredientValue;
    }

}
