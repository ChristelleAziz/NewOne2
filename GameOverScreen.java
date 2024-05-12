package org.folifac;

import greenfoot.*;

public class GameOverScreen extends World {

    public GameOverScreen() {
        super(600, 400, 1);
        GreenfootImage background = new GreenfootImage("game_over_background.jpg");
        setBackground(background);
        showText("Game Over", getWidth() / 2, getHeight() / 2);
    }
}
