package com.dunzo.Providers;

import javax.lang.model.util.ElementScanner6;
import javax.management.RuntimeErrorException;

import com.dunzo.Providers.composers.BlackTeaBeverageComposer;
import com.dunzo.Providers.composers.GreenTeaComposer;
import com.dunzo.Providers.composers.HotCoffeeComposer;
import com.dunzo.Providers.composers.HotTeaComposer;
import com.dunzo.Providers.composers.Icomposer;
import com.dunzo.constants.BeverageConst;

import javafx.util.converter.BigIntegerStringConverter;

public class BeverageComposerProvider {

    BlackTeaBeverageComposer blackTeaBeverageComposer;
    GreenTeaComposer greenTeaComposer;
    HotCoffeeComposer hotCoffeeComposer;
    HotTeaComposer hotTeaComposer;

    private static BeverageComposerProvider instance;
    private BeverageComposerProvider() {
    }

    public static BeverageComposerProvider getInstance()
    {
      if (instance == null)
      {
        synchronized(BeverageComposerProvider.class)
        {
          if(instance==null)
          {
            // if instance is null, initialize
            instance = new BeverageComposerProvider();
          }
        }
      }
      return instance;
    }


    public  Icomposer getBeverageComposer(String beverageType) {
      if(beverageType.equals(BeverageConst.BEVERAGE_BLACK_TEA.getbeverageValue()))
          return blackTeaBeverageComposer;
    
      if(beverageType.equals(BeverageConst.BEVERAGE_GREEN_TEA.getbeverageValue()))
        return greenTeaComposer;

      if(beverageType.equals(BeverageConst.BEVERAGE_HOT_COFFEE.getbeverageValue()))
        return hotCoffeeComposer;

      if(beverageType.equals(BeverageConst.BEVERAGE_HOT_TEA.getbeverageValue()))
        return hotTeaComposer;

    else{
        throw new RuntimeErrorException(null, "We dont support This beverage");
    }
}

    public void setBeverageComposer(String beverageType,Icomposer beverageComposer)
    {
        if(beverageType.equals(BeverageConst.BEVERAGE_BLACK_TEA.getbeverageValue()))
        blackTeaBeverageComposer=(BlackTeaBeverageComposer) beverageComposer;
  
    if(beverageType.equals(BeverageConst.BEVERAGE_GREEN_TEA.getbeverageValue()))
      greenTeaComposer=(GreenTeaComposer)beverageComposer;

    if(beverageType.equals(BeverageConst.BEVERAGE_HOT_COFFEE.getbeverageValue()))
      hotCoffeeComposer=(HotCoffeeComposer)beverageComposer;

    if(beverageType.equals(BeverageConst.BEVERAGE_HOT_TEA.getbeverageValue()))
      hotTeaComposer=(HotTeaComposer)beverageComposer;

    }

      
    
}
