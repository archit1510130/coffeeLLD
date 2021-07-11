package com.dunzo.Providers.composers;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.dunzo.constants.BeverageConst;

public class HotCoffeeComposer implements Icomposer {

    Map<String,Integer> composers=new Hashtable<>();// this should be in some kind of storage

    @Override
    public String getBeverageType() {
        return BeverageConst.BEVERAGE_HOT_COFFEE.getbeverageValue();
    }

    @Override
    public Map<String, Integer> getRulesForComposer() {
        return this.composers;
    }

    @Override
    public void setRulesForComposer(Map<String, Integer> ingredientsMap) {
        composers=ingredientsMap;   
    }
    
}
