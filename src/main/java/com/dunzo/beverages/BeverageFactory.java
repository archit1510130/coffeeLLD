package com.dunzo.beverages;

import com.dunzo.constants.BeverageConst;

public class BeverageFactory {
    public static Ibeverage getBeverage(String beverageType) {
        if (BeverageConst.BEVERAGE_GREEN_TEA.getbeverageValue().equals(beverageType))
            return new GreenTea();
        if (BeverageConst.BEVERAGE_BLACK_TEA.getbeverageValue().equals(beverageType))
            return new BlackTeaBeverage();
        if (BeverageConst.BEVERAGE_HOT_TEA.getbeverageValue().equals(beverageType))
            return new HotTea();
        if (BeverageConst.BEVERAGE_HOT_COFFEE.getbeverageValue().equals(beverageType))
            return new HotCoffee();

        return null;
    }

}
