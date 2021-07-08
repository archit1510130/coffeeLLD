package com.dunzo;

import com.dunzo.constants.BeverageConst;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println(BeverageConst.BEVERAGE_BLACK_TEA.getbeverageValue());
    }
}
