package com.dunzo.Storage;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


// This should be singleton Class 
public class IngredientStorage {

    private Hashtable<String ,Integer> ingredientsStorage=new Hashtable<>();
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

    public Hashtable<String,Integer> getStorage(){
        return this.ingredientsStorage;
    }

    public void setStorage( Hashtable<String,Integer> iMap){
        this.ingredientsStorage=iMap;
    }

}
