import greenfoot.*;
import java.util.List;

public class Bob extends Actor {
    private int verticalSpeed = 0;
    private int acceleration = 1;
    private int jumpHeight = -10;
    private int livesCount = 5;
    private int bulletsCount = 10;
    private int animationSpeed;
    private int frame = 1;
    private boolean collisionDetected = false;

    private GreenfootImage bob1 = new GreenfootImage("Bob.png");
    private GreenfootImage bob2 = new GreenfootImage("Bob2.png");
    private GreenfootImage bob3 = new GreenfootImage("RBob1.png");
    private GreenfootImage bob4 = new GreenfootImage("RBob2.png");

    public void act() {
        handleMovement();
        checkFalling();
        collectItems();
        adjustWorldPosition();
        checkCollision();
    }

    private boolean isTouchingMinion() {
        Minion minion = (Minion) getOneIntersectingObject(Minion.class);
        return minion != null && getX() < minion.getX();
    }
    
    private boolean isTouchingSpike() {
        return !getObjectsInRange(50, Spike.class).isEmpty();
    }

    private boolean isTouchingMeteorite() {
        return !getObjectsInRange(50, Meteorite2.class).isEmpty();
    }
    
    private void handleMovement() {
        animationSpeed++;
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) {
            moveRight();
        }
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) {
            moveLeft();
        }
        if ((Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("w")) && verticalSpeed == 0) {
            jump();
        }
        if (bulletsCount > 0 && Greenfoot.mouseClicked(null)) {
            shootBullet();
        }
    }

    private void moveRight() {
        move(3);
        animateRight();
    }

    private void moveLeft() {
        move(-4);
        animateLeft();
    }

    private void jump() {
        verticalSpeed = jumpHeight;
        fall();
        playSound("jump10.wav");
    }

        private void shootBullet() {
        getWorld().addObject(new Bullet(), getX(), getY());
        playSound("shoot.wav");
        bulletsCount--;
        loseBulletDisplayed();

        Minion minion = (Minion) getOneIntersectingObject(Minion.class);
        if (minion != null) {
            getWorld().removeObject(minion);
        }
    }
    
    private void playSound(String filename) {
        GreenfootSound sound = new GreenfootSound(filename);
        sound.setVolume(70);
        sound.play();
    }

    private void adjustWorldPosition() {
        for (Actor object : getWorld().getObjects(Actor.class)) {
            if (object != this && !(object instanceof Live) && !(object instanceof BulletDisplayed)
                    && !(object instanceof PlanetBackground) && !(object instanceof Castle)
                    && !(object instanceof King) && !(object instanceof Mam) && !(object instanceof Label) && !(object instanceof Star)) {
                object.move(-3);
            }
        }
    }

    private void fall() {
        setLocation(getX(), getY() + verticalSpeed);
        verticalSpeed += acceleration;
    }

    private void checkFalling() {
        if (!onGround() && !onPlanet()) {
            fall();
        } else {
            verticalSpeed = 0;
        }
    }
        
        private boolean onGround() {
        Platform platform = (Platform)getOneObjectAtOffset(0, getImage().getHeight() / 2, Platform.class);
        return platform != null && getX() >= platform.getX() - platform.getImage().getWidth() / 2 &&
            getX() <= platform.getX();
    }

    private boolean onPlanet() {
        return getOneObjectAtOffset(0, getImage().getHeight() / 2, Planet.class) != null;
    }

    private void collectItems() {
        collectCoin();
        collectBullet();
    }

     private void collectCoin() {
        Actor coin = getOneIntersectingObject(Coin.class);
        if (coin != null) {
            getWorld().removeObject(coin);
            if (getWorld() instanceof Level_1) {
                ((Level_1) getWorld()).changeCoinsCounter(1);
            } else if (getWorld() instanceof Level_2) {
                ((Level_2) getWorld()).changeCoinsCounter(1);
            } else if (getWorld() instanceof Level_3) {
                ((Level_3) getWorld()).changeCoinsCounter(1);
            } else if (getWorld() instanceof Level_4) {
                ((Level_4) getWorld()).changeCoinsCounter(1);
            } else if (getWorld() instanceof Level_5) {
                ((Level_5) getWorld()).changeCoinsCounter(1);
            }
            GreenfootSound pickupCoinSound = new GreenfootSound("pickupCoin.wav");
            pickupCoinSound.play();
        }
    }
    private void collectBullet() {
        Actor bulletAppearing = getOneIntersectingObject(BulletAppearing.class);
        if (bulletAppearing != null) {
            getWorld().removeObject(bulletAppearing);
            bulletsCount++;
            addBulletDisplayed();
            playSound("pickupCoin.wav");
        }
    }

    private void checkCollision() {
        Actor badGuy = getOneIntersectingObject(BadGuys.class);
        if (badGuy != null && !collisionDetected) {
            if (badGuy instanceof Minion || badGuy instanceof Spike || badGuy instanceof Meteorite2) {
                if (isTouchingMinion() || isTouchingSpike() || isTouchingMeteorite()) {
                    loseLife();
                    collisionDetected = true;
                    playSound("hurt.wav");
                }
            }
        } else if (badGuy == null) {
            collisionDetected = false;
        }
    }

    private void loseLife() {
        livesCount--;
        removeLive();
        if (livesCount == 0) {
            getWorld().removeObject(this);
            Greenfoot.setWorld(new Background2());
        }
        temporaryInvincibility();
    }

    private void temporaryInvincibility() {
        GreenfootImage image = getImage();
        long startTime = System.currentTimeMillis();
        boolean visible = true;
        while (System.currentTimeMillis() - startTime < 1800) {
            image.setTransparency(visible ? 0 : 255);
            visible = !visible;
            Greenfoot.delay(5);
        }
        image.setTransparency(255);
    }

    private void removeLive() {
        List<Live> hearts = getWorld().getObjects(Live.class);
        if (!hearts.isEmpty()) {
            getWorld().removeObject(hearts.get(hearts.size() - 1));
        }
    }

    private void loseBulletDisplayed() {
        removeBulletDisplayed();
    }

    private void removeBulletDisplayed() {
        List<BulletDisplayed> bulletsDisplayed = getWorld().getObjects(BulletDisplayed.class);
        if (!bulletsDisplayed.isEmpty()) {
            getWorld().removeObject(bulletsDisplayed.get(bulletsDisplayed.size() - 1));
        }
    }

    private void addBulletDisplayed() {
        List<BulletDisplayed> bulletsDisplayed = getWorld().getObjects(BulletDisplayed.class);
        if (bulletsDisplayed.size() < 10) {
            int newX = 40 + bulletsDisplayed.size() * 20;
            BulletDisplayed newBullet = new BulletDisplayed();
            getWorld().addObject(newBullet, newX, 90);
        }
    }
    
    public void animateRight()
    {
        setImage(frame == 1 ? bob1 : bob2);
        frame = frame == 1 ? 2 : 1;
    }
    
    public void animateLeft()
    {
        setImage(frame == 1 ? bob3 : bob4);
        frame = frame == 1 ? 2 : 1;
    }
}