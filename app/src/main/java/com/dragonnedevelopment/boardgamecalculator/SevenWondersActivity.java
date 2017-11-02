package com.dragonnedevelopment.boardgamecalculator;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.dragonnedevelopment.boardgamecalculator.Adapters.SevenWondersCursorAdapter;
import com.dragonnedevelopment.boardgamecalculator.model.SevenWondersPlayer;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

/**
 * BoardGameCalculator Created by Muir on 26/10/2017.
 *
 * displays list of players for 7 Wonders
 */

public class SevenWondersActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PLAYER_LOADER = 0;
    SevenWondersCursorAdapter cursorAdapter;

    ExpandableRelativeLayout expandableLayoutSevenWonders;
    RelativeLayout scoreCardLayout;
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

        // find the ListView which will be populated with the player data
        final ListView playerListView = (ListView) findViewById(R.id.sevenWondersList);


        // find and set empty view on the ListView, so that it only shows when the list has 0 items
        View emptyView = findViewById(R.id.sevenWondersEmptyView);
        playerListView.setEmptyView(emptyView);

        /*
         * Set up an Adapter to create a list item for each player in the Cursor.
         */
        cursorAdapter = new SevenWondersCursorAdapter(this, null);
        playerListView.setAdapter(cursorAdapter);

        // Kick off the loader
        getSupportLoaderManager().initLoader(PLAYER_LOADER, null, this);

        // Set up FAB to add player
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fabSevenWondersAddPlayer);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add player
                sevenWondersPlayer = new SevenWondersPlayer(playerName, playerTotalScore, playerMilitary,
                        playerTreasury, playerWonder, playerCivilian, playerScientificTotalScore,
                        playerIdenticalSetsTotalScore, playerProtractorSets, playerTabletSets,
                        playerCogSets, playerDifferentSets, playerCommercial, playerGuilds);

                
            }
        });

    }

    public void expandableButton1(View view) {
        expandableLayoutSevenWonders = (ExpandableRelativeLayout) findViewById(R.id.expandableScoreCalculator1);
        expandableLayoutSevenWonders.toggle(); // toggle expand and collapse
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


}
