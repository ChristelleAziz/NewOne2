import greenfoot.*;
import java.util.List;

public class Level_3 extends Levels {
    private GreenfootSound backgroundMusic;
    private int scrollOffset = 0;
    private int minionSpawnTimer = Greenfoot.getRandomNumber(200) + 100;
    private Label coinsCounter;

    public Level_3() {    
        prepare();
        //setupBackgroundMusic();
    }
    
     public void setCoinsCounterLabel(String text) {
        coinsCounter.setText(text);
    }

    public String getCoinsCounterLabel() {
        return coinsCounter.getText();
    }
    
    public void changeCoinsCounter(int amount) {
        String labelText = coinsCounter.getLabel();
        int index = labelText.indexOf(":");
        String scoreStr = labelText.substring(index + 2);
        int score = Integer.parseInt(scoreStr);
        int newScore = score + amount;
        coinsCounter.setLabel("Coins: " + newScore);
    }

    private void respawnMinions() {
        int numMinions = getObjects(Minion.class).size();
        int maxMinions = 2; 

        if (numMinions < maxMinions) {
            addMinions();
        }
    }

    public void act() {
        spawnCoins();
        spawnBullets();
        checkMinionRespawn();
        super.act();
        adjustActorPositions();
        minionAddedThisAct = false;
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

    //public void stopped() {
    ///    backgroundMusic.stop();
    //}

    private void prepare() {
        addPlanetBackground();
        addKing();
        addMam();
        addCastle();
        addStars();
        addClouds();
        addPlatforms();
        addPlanet();
        addSpikes();
        addMinions();
        addBob();
        addLives();
        addBulletsDisplayedFirst();
        //addCoinsCounter();
    }

    private void addClouds() {
        addObject(new Cloud(), 220, 160);
        addObject(new Cloud(), 690, 110);
    }

    private void addPlanetBackground() {
        addObject(new PlanetBackground(), 530, 610);
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
        addObject(new Bob(), 172, 491);
    }

    private void addSpikes() {
        addObject(new Spike(), 184, 626);
        addObject(new Spike(), 925, 626);
    }

    private void addMinions() {
        addObject(new Minion(), getWidth() - 1, 615);
    }

    private void addLives() {
        for (int i = 0; i < 5; i++) {
            addObject(new Live(), 50 + i * 40, 50);
        }
    }

    private void addBulletsDisplayedFirst() {
        for (int i = 0; i < 10; i++) {
            addObject(new BulletDisplayed(), 40 + i * 20, 90);
        }
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
    
    private void addCoinsCounter() {
        coinsCounter = new Label("Coins: 0");
        addObject(coinsCounter, 860, 60);
    }
}

