package com.dunzo.Providers.composers;

import java.util.Map;

public interface Icomposer {
    
    public String getBeverageType();
    public Map<String, Integer> getRulesForComposer();
    public void setRulesForComposer(Map<String, Integer> ingredientsMap);
    
}
