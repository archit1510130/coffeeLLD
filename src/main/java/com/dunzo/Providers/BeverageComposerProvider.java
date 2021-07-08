package com.dunzo.Providers;

import com.dunzo.Providers.composers.BlackTeaBeverageComposer;
import com.dunzo.Providers.composers.Icomposer;
import com.dunzo.constants.BeverageConst;

public class BeverageComposerProvider {
    public static Icomposer getBeverageComposer(String beverageType) {
      if(beverageType.equals(BeverageConst.BEVERAGE_BLACK_TEA.getbeverageValue()))
      {
          return new BlackTeaBeverageComposer();
      }
      return null;
    }
}
