package com.dragonnedevelopment.boardgamecalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dragonnedevelopment.boardgamecalculator.model.SevenWondersPlayer;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

/**
 * BoardGameCalculator Created by Muir on 26/10/2017.
 */

public class SevenWondersActivity extends AppCompatActivity {

    ExpandableRelativeLayout expandableLayoutSevenWonders;
    SevenWondersPlayer sevenWondersPlayer;

    String playerName;
    int playerTotalScore;
    int playerMilitary;
    int playerTreasury;
    int playerWonder;
    int playerCivilian;
    int playerScientificTotalScore;
    int playerIdenticalSetsTotalScore;
    int playerProtractorSets;
    int playerTabletSets;
    int playerCogSets;
    int playerDifferentSets;
    int playerCommercial;
    int playerGuilds;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven_wonders);
    }

    public void expandableButton1(View view) {
        expandableLayoutSevenWonders = (ExpandableRelativeLayout) findViewById(R.id.expandableScoreCalculator1);
        expandableLayoutSevenWonders.toggle(); // toggle expand and collapse
    }


    // Set up the item click listener for adding a sevenWondersPlayer
    public void addPlayer(View view) {
        sevenWondersPlayer = new SevenWondersPlayer(playerName, playerTotalScore, playerMilitary,
                playerTreasury, playerWonder, playerCivilian, playerScientificTotalScore,
                playerIdenticalSetsTotalScore, playerProtractorSets, playerTabletSets,
                playerCogSets, playerDifferentSets, playerCommercial, playerGuilds);


    }

}
