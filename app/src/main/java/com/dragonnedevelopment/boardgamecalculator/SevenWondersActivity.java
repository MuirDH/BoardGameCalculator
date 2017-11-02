package com.dragonnedevelopment.boardgamecalculator;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.dragonnedevelopment.boardgamecalculator.Adapters.SevenWondersCursorAdapter;
import com.dragonnedevelopment.boardgamecalculator.data.GamesContract.PlayerEntry;
import com.dragonnedevelopment.boardgamecalculator.model.SevenWondersPlayer;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

/**
 * BoardGameCalculator Created by Muir on 26/10/2017.
 * <p>
 * displays list of players for 7 Wonders
 */

public class SevenWondersActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PLAYER_LOADER = 0;
    SevenWondersCursorAdapter cursorAdapter;

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

        // find the ListView which will be populated with the player data
        final ListView playerListView = (ListView) findViewById(R.id.sevenWondersList);

        final RelativeLayout scoreCardLayout = (RelativeLayout) findViewById(R.id.playerScoreCard);


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

                ContentValues values = new ContentValues();

                /**
                 * Insert a new row for the new player into the provider using the ContentResolver.
                 * Use the {@link PlayerEntry.CONTENT_URI_SEVENWONDERS} to indicate that we want to
                 * insert into the 7 Wonders database table. Receive the new content URI that will
                 * allow us to access the player's data in the future.
                 */
                //Uri newUri = getContentResolver().insert(PlayerEntry.CONTENT_URI_SEVENWONDERS, values);
            }
        });

    }

    // Set up the expandable button click listener
    public void expandableButton1(AdapterView<?> adapterView, View view, int position, long id) {
        expandableLayoutSevenWonders = (ExpandableRelativeLayout) findViewById(R.id.expandableScoreCalculator1);
        expandableLayoutSevenWonders.toggle(); // toggle expand and collapse

        /*
         * form the content URI that represents the specific player that was clicked on, by
         * appending the "id" (passed as input to this method) onto the
         * {@link PlayerEndter#CONTENT_URI_SEVENWONDERS}. For example, the URI would be
         * "content://com.dragonnedevelopment.boardgamecalculator/sevenwonders/2" if the player
         * with ID 2 was clicked on
         */

        Uri currentPlayerUri = ContentUris.withAppendedId(PlayerEntry.CONTENT_URI_SEVENWONDERS, id);

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Define a projection that specifies the columns from the table we care about
        String[] projection = {
                PlayerEntry._ID,
                PlayerEntry.COLUMN_NAME,
                PlayerEntry.COLUMN_MILITARY_CONFLICTS,
                PlayerEntry.COLUMN_TREASURY,
                PlayerEntry.COLUMN_WONDER,
                PlayerEntry.COLUMN_CIVILIAN_STRUCTURES,
                PlayerEntry.COLUMN_SCIENTIFIC_STRUCTURES_TOTAL,
                PlayerEntry.COLUMN_IDENTICAL_SCIENTIFIC_SETS,
                PlayerEntry.COLUMN_PROTRACTOR_SETS,
                PlayerEntry.COLUMN_TABLET_SETS,
                PlayerEntry.COLUMN_COGS_SETS,
                PlayerEntry.COLUMN_DIFFERENT_SCIENTIFIC_SETS,
                PlayerEntry.COLUMN_COMMERCIAL_STRUCTURES,
                PlayerEntry.COLUMN_GUILDS};

        // This loader will execute the contentProvider's query method on a background thread.
        return new CursorLoader(
                this,             // Parent activity context
                PlayerEntry.CONTENT_URI_SEVENWONDERS,  // Provider activity context
                projection,               // Columns to include in the resulting Cursor
                null,            // No selection clause
                null,         // No selection arguments
                null);          // Default sort order

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        /*
         * Update {@link SevenWondersCursorAdapter} with this new cursor containing updated player
         * data
         */
        cursorAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Callback called when the data needs to be deleted
        cursorAdapter.swapCursor(null);

    }


}
