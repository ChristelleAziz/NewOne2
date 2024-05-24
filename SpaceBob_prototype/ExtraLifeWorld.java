import greenfoot.*;

public class ExtraLifeWorld extends World {
    private Bob bob;
    private CoinManager coinManager;

    public ExtraLifeWorld(Bob bob, CoinManager coinManager) {
        super(600, 400, 1);
        this.bob = bob;
        this.coinManager = coinManager;
        showText(" Do you want to spend 10 coins for an extra life? Press Y for Yes, N for No. ", 300, 200);
    }

    public void act() {
        if (Greenfoot.isKeyDown("y")) {
            if (coinManager.getCoins() >= 10) {
                coinManager.spendCoins(10);
                //bob.gainLife();
                //Greenfoot.setWorld(bob.getPreviousWorld());
            } else {
                showText("Not enough coins! ", 300, 250);
            }
        } else if (Greenfoot.isKeyDown("n")) {
            Greenfoot.setWorld(new Background2());
        }
    }
}
