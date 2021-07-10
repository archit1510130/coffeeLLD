package com.dunzo.Providers.composers;

import java.util.HashMap;
import java.util.Map;

import com.dunzo.constants.BeverageConst;

public class GreenTeaComposer implements Icomposer {

    Map<String,Integer> composers=new HashMap<>();// this should be in some kind of storage

    @Override
    public String getBeverageType() {
        return BeverageConst.BEVERAGE_GREEN_TEA.getbeverageValue();
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
