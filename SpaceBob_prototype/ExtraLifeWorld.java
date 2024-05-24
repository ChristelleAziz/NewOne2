import greenfoot.*;

public class ExtraLifeWorld extends World {

    private Bob bob;

    public ExtraLifeWorld(Bob bob) {
        super(600, 400, 1);
        this.bob = bob;
        showText("Do you want to spend 10 coins for an extra life? (Y/N)", 300, 200);
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("y") && bob.getCoins() >= 10) {
            bob.spendCoins(10);
            bob.gainLife();
            Greenfoot.setWorld(bob.getPreviousWorld());
            } else if (Greenfoot.isKeyDown("n") || bob.getCoins() < 10) {
            Greenfoot.setWorld(new Background2());
        }
    }
}
