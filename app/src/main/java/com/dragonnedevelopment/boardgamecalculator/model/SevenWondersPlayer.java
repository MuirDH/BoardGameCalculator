package com.dragonnedevelopment.boardgamecalculator.model;

/**
 * BoardGameCalculator Created by Muir on 30/10/2017.
 */

/**
 * {@link SevenWondersPlayer} represents a single player of a Seven Wonders game. Inherits from
 * {@link Player} parent class.
 */
public class SevenWondersPlayer extends Player {
    /*
     * Tallied by the player at the end of each round. Player adds their Victory and Defeat tokens
     * (can be a negative number)
     */
    private int militaryConflicts;

    /*
     * Added up at the end of the game. 3 coins in a player's possession equals one point.
     * Coins/3, remainder discarded.
     */
    private int treasury;

    // Tallied at end of game, according to what the player's wonder card dictates.
    private int wonder;

    // Points on cards are added up at end of game.
    private int civilianStructures;

    // Total of the two types of scoring for Science (green cards)
    private int scientificTotal;

    // Total of points from Science Symbols
    private int identicalScienceSetsTotal;

    // Number of identical symbols, squared. E.g. 3 cog symbols = 9 points.
    private int protractorSets;
    private int tabletSets;
    private int cogSets;

    // Number of sets of 3 of the different symbols * 7 E.g. 1 set = 7, 2 sets = 14, etc...
    private int differentScienceSets;

    // Tallied according to instructions of orange commercial card.
    private int commercialStructures;

    // Tallied according to instructions on purple guild card.
    private int guilds;


    public SevenWondersPlayer(String nameOfPlayer, int scoreOfPlayer, int militaryConflictsTally,
                              int treasuryTally, int wonderTally, int civilianStructureTally,
                              int scientificTotalScore, int identicalScienceSetsTally,
                              int protractorSetsTally, int tabletSetsTally, int cogSetsTally,
                              int differentScienceSetsTally, int commercialStructureTally,
                              int guildTally) {

        super(nameOfPlayer, scoreOfPlayer);
        militaryConflicts = militaryConflictsTally;
        treasury = treasuryTally;
        wonder = wonderTally;
        civilianStructures = civilianStructureTally;
        scientificTotal = scientificTotalScore;
        identicalScienceSetsTotal = identicalScienceSetsTally;
        protractorSets = protractorSetsTally;
        tabletSets = tabletSetsTally;
        cogSets = cogSetsTally;
        differentScienceSets = differentScienceSetsTally;
        commercialStructures = commercialStructureTally;
        guilds = guildTally;

    }

    public int getMilitaryConflicts() {
        return militaryConflicts;
    }

    public int getTreasury() {
        return treasury;
    }

    public int getWonder() {
        return wonder;
    }

    public int getCivilianStructures() {
        return civilianStructures;
    }

    public int getScientificTotal() {
        return scientificTotal;
    }

    public int getIdenticalScienceSetsTotal() {
        return identicalScienceSetsTotal;
    }

    public int getProtractorSets() {
        return protractorSets;
    }

    public int getTabletSets() {
        return tabletSets;
    }

    public int getCogSets() {
        return cogSets;
    }

    public int getDifferentScienceSets() {
        return differentScienceSets;
    }

    public int getCommercialStructures() {
        return commercialStructures;
    }

    public int getGuilds() {
        return guilds;
    }
}
