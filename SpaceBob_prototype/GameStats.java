import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameStats here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

    /**
     * Constructor for objects of class GameStats.
     * 
     */
    public class GameStats {
    private static int coinsCollected = 0;

    public static int getCoinsCollected() {
        return coinsCollected;
    }

    public static void setCoinsCollected(int coins) {
        coinsCollected = coins;
    }

    public static void addCoins(int coins) {
        coinsCollected += coins;
    }
}
