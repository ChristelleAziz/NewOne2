import greenfoot.*;

    public class CoinManager extends Actor {
        private int coins = 0;

    public int getCoins() {
        return coins;
    }

    public void spendCoins(int amount) {
        coins -= amount;
    }

    public void addCoins(int amount) {
        coins += amount;
    }

    public void act() {
    }
    
    public CoinManager getCoinManager() {
    MyGameWorld world = (MyGameWorld) getWorld();
    if( world != null) {
        return world.getCoinManager();
    }
    return null;
    }
}