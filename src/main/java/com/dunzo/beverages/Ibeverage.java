package com.dunzo.beverages;

import com.dunzo.Providers.composers.Icomposer;

public interface Ibeverage {
    public String getType();
    public void doAction();
    public Icomposer getComposerDetails();
}
