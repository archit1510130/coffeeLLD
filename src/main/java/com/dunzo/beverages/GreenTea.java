package com.dunzo.beverages;

import com.dunzo.Providers.BeverageComposerProvider;
import com.dunzo.Providers.composers.Icomposer;
import com.dunzo.constants.BeverageConst;

public class GreenTea  implements Ibeverage{

    Icomposer composerDetails;
    // here you need to set composer details using providers
    public GreenTea()
    {
        this.composerDetails=BeverageComposerProvider.getInstance().getBeverageComposer(this.getType());
    }

    @Override
    public String getType() {
        return BeverageConst.BEVERAGE_GREEN_TEA.getbeverageValue();
    }

    @Override
    public void doAction() {
        System.out.println("I am drinking Black Tea");
        
    }

    @Override
    public Icomposer getComposerDetails() {
        // composer factory will return composer details for each beverage
        return composerDetails;
    }
    
}
