package com.dragonnedevelopment.boardgamecalculator.model;

/**
 * BoardGameCalculator Created by Muir on 01/11/2017.
 */

/**
 * Parent class for all players. All players in all games in the BoardGameCalculator app have a name
 * and a total score. All game player objects inherit from this parent class.
 */
public class Player {

    private String playerName;
    private int playerScoreTotal;

    public Player(String nameOfPlayer, int scoreOfPlayer) {
        playerName = nameOfPlayer;
        playerScoreTotal = scoreOfPlayer;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerScore() {
        return playerScoreTotal;
    }

}
