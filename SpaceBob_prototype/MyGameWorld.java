import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyGameWorld extends World {
    private CoinManager coinManager;
    private Bob bob;

    public MyGameWorld() {
        super(600, 400, 1);
        coinManager = new CoinManager();
        addObject(coinManager, 50, 50); // Add CoinManager to the world
        bob = new Bob();
        addObject(bob, 300, 200);
    }

    public CoinManager getCoinManager() {
        return coinManager;
    }
}