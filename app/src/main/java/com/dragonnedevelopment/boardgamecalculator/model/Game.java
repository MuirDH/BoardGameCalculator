package com.dragonnedevelopment.boardgamecalculator.model;

/**
 * BoardGameCalculator Created by Muir on 20/10/2017.
 */

/**
 * {@link Game represents a game calculator that the user can select from the menu. It contains a
 * game name and associated image.}
 */

public class Game {

    private String gameName;
    private int imageResourceId;

    public Game(String nameOfGame, int imageResource ) {
        gameName = nameOfGame;
        imageResourceId = imageResource;
    }

    public String getGameName() {
        return gameName;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
