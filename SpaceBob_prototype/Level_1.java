import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class Level_1 extends Levels {
    Levels thisGame;
    private GreenfootSound backgroundMusic;
    private int scrollOffset = 0;
    private int minionSpawnTimer = Greenfoot.getRandomNumber(200) + 100;

    public Level_1() {
        prepare();
        setupBackgroundMusic();;
        // Set the paint order to ensure Bob is always in front
        setPaintOrder(BulletDisplayed.class, /*CoinsCounter.class, */ Live.class, Armor.class, Label.class, Bob.class, Minion.class, Coin.class, BulletAppearing.class /* other classes if necessary */);
        Bob bob = new Bob();
    }

    public void act() {
        spawnCoins();
        spawnArmors();
        spawnBullets();
        checkMinionRespawn();
        super.act();
        adjustActorPositions();
        minionAddedThisAct = false;
        showText("Coins: " + thisGame.coinsAmount, 860, 75);
        showText("Enemies Left: " + thisGame.enemiesLeft, 860, 40);
    }
    
    private void spawnArmors() {
        if (Greenfoot.getRandomNumber(3000) <= 2) {
            addObject(new ArmorAppearing(), getWidth() - 1, Greenfoot.getRandomNumber(277) + 343);
        }
    }
    
    public int getScrollOffset() {
        // You can return a constant scroll speed for simplicity
        // Replace 5 with your desired scroll speed value
        return 5;
    }

    private void respawnMinions() {
        int numMinions = getObjects(Minion.class).size();
        int maxMinions = 4; 

        if (numMinions < maxMinions) {
            addMinions();
        }
    }

    private void adjustActorPositions() {
        List<Actor> actors = getObjects(Actor.class);
        for (Actor actor : actors) {
            actor.setLocation(actor.getX() - scrollOffset, actor.getY());
        }
        scrollOffset = 0;
    }

    private boolean minionAddedThisAct = false;

    private void checkMinionRespawn() {
        minionSpawnTimer--;
        if (minionSpawnTimer <= 0 && !minionAddedThisAct) {
            addMinions();
            minionSpawnTimer = Greenfoot.getRandomNumber(600) + 300;
            minionAddedThisAct = true;
        }
    }

    public void scroll(int amount) {
        scrollOffset += amount;
    }

    private Planet getPlanet() {
        List<Planet> planets = getObjects(Planet.class);
        return planets.isEmpty() ? null : planets.get(0);
    }

    private Bob getBob() {
        List<Bob> bobs = getObjects(Bob.class);
        return bobs.isEmpty() ? null : bobs.get(0);
    }

    private void setupBackgroundMusic() {
        backgroundMusic = new GreenfootSound("background.mp3");
        adjustVolume(backgroundMusic, 50);
        playBackgroundMusic();
    }

    private void playBackgroundMusic() {
        if (!backgroundMusic.isPlaying()) {
            backgroundMusic.playLoop();
        }
    }

    private void adjustVolume(GreenfootSound sound, int volume) {
        sound.setVolume(volume);
    }

    public void stopped() {
        backgroundMusic.stop();
    }

    private void prepare() {
        addPlanetBackground();
        addPlanet6();
        addPlanet5();
        addPlanet1();
        addPlanet2();
        addKing();
        addMam();
        addCastle();
        addStars();
        addClouds();
        addPlatforms();
        addPlanet();
        addSpikes();
        addBob();
        addLives();
        addBulletsDisplayedFirst();
        addArmorsDisplayedFirst();
    }

    private void addLives() {
        for (int i = 0; i < 3; i++) {
            addObject(new Live(), 50 + i * 40, 50);
        }
    }

    private void addBulletsDisplayedFirst() {
        for (int i = 0; i < 10; i++) {
            addObject(new BulletDisplayed(), 40 + i * 20, 90);
        }
    }

    private void addArmorsDisplayedFirst() {
        for (int i = 0; i < 10; i++) {
            addObject(new ArmorDisplayed(), 40 + i * 20, 130);
        }
    }
    
    private void addClouds() {
        addObject(new Cloud(), 220, 160);
        addObject(new Cloud(), 690, 110);
    }

    private void addPlanetBackground() {
        addObject(new PlanetBackground(), 530, 610);
    }

    private void addPlanet6() {
        addObject(new Planet6(), 836, 124);
    }
    
    private void addPlanet5() {
        addObject(new Planet5(), 170, 400);
    }
    
    private void addPlanet1() {
        addObject(new Planet1(), 620, 440);
    }
    
    private void addPlanet2() {
        addObject(new Planet2(), 417, 180);
    }
    
    private void addPlanet4() {
        addObject(new Planet5(), 170, 400);
    }
    
    private void addCastle() {
        addObject(new Castle(), 523, 533);
    }

    private void addMam() {
        addObject(new Mam(), 516, 539);
    }

    private void addKing() {
        addObject(new King(), 531, 539);
    }

    private void addStars() {
        addObject(new Star(), 621, 103);
        addObject(new Star(), 49, 82);
        addObject(new Star(), 320, 260);
        addObject(new Star(), 960, 170);
        addObject(new Star(), 732, 200);
        addObject(new Star(), 303, 35);
        addObject(new Star(), 81, 210);
    }

    private void addPlatforms() {
        addObject(new Platform(), 172, 563);
        addObject(new Platform(), 600, 499);
    }

    private void addPlanet() {
        addObject(new Planet(), 490, 758);   
    }

    private void addBob() {
        addObject(new Bob(), 110, 510);
    }

    private void addSpikes() {
        addObject(new Spike(), 500, 626);
        addObject(new Spike(), 925, 626);
    }

    private void addMinions() {
        addObject(new Minion(), getWidth() - 1, 615);
    }

    private void spawnCoins() {
        if (Greenfoot.getRandomNumber(1000) <= 2) {
            addObject(new Coin(), getWidth() - 1, Greenfoot.getRandomNumber(277) + 343);
        }
    }
   
    private void spawnBullets() {
        if (Greenfoot.getRandomNumber(1500) <= 2) {
            addObject(new BulletAppearing(), getWidth() - 1, Greenfoot.getRandomNumber(277) + 343);
        }
    }
}