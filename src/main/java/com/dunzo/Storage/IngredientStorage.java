package com.dunzo.Storage;

import java.util.HashMap;
import java.util.Map;


// This should be singleton Class 
public class IngredientStorage {

    private Map<String ,Integer> ingredientsStorage=new HashMap<>();
    private static IngredientStorage instance;

    private IngredientStorage() {
    }

    public static IngredientStorage getInstance()
    {
      if (instance == null)
      {
        synchronized(IngredientStorage.class)
        {
          if(instance==null)
          {
            // if instance is null, initialize
            instance = new IngredientStorage();
          }
        }
      }
      return instance;
    }

    public Map<String,Integer> getStorage(){
        return this.ingredientsStorage;
    }

    public void setStorage( Map<String,Integer> iMap){
        this.ingredientsStorage=iMap;
    }

}
