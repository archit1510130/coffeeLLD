package com.dunzo.constants;

public enum BeverageConst {
    BEVERAGE_GREEN_TEA ("Green Tea"),
    BEVERAGE_BLACK_TEA ("Black Tea"),
    BEVERAGE_HOT_TEA ("Hot Tea"),
    BEVERAGE_HOT_COFFEE ("Hot Coffee");

    private String beverageValue;
    public String getbeverageValue()
    {
        return this.beverageValue;
    }

    private BeverageConst(String beverageValue)
    {
        this.beverageValue = beverageValue;
    }
    
}
