package com.dragonnedevelopment.boardgamecalculator;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.*;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dragonnedevelopment.boardgamecalculator.Adapters.SevenWondersCursorAdapter;
import com.dragonnedevelopment.boardgamecalculator.data.GamesContract.PlayerEntry;

/**
 * BoardGameCalculator Created by Muir on 26/10/2017.
 * <p>
 * displays list of players for 7 Wonders
 */

public class SevenWondersActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PLAYER_LOADER = 0;
    SevenWondersCursorAdapter cursorAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven_wonders);

        // Set up FAB to open 7WondersEditorActivity
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fabSevenWondersAddPlayer);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SevenWondersActivity.this, SevenWondersEditorActivity.class);
                startActivity(intent);
            }
        });

        // Find the ListView which will be populated with the player data
        ListView playerListView = (ListView) findViewById(R.id.sevenWondersList);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items
        View emptyView = findViewById(R.id.sevenWondersEmptyView);
        playerListView.setEmptyView(emptyView);

        /*
         * Set up an Adapter to create a list item for each row of player data in the Cursor. There
         * is no player data yet (until the loader finishes) so pass in null for the Cursor.
         */
        cursorAdapter = new SevenWondersCursorAdapter(this, null);
        playerListView.setAdapter(cursorAdapter);

        // Set up the item click listener
        playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Create a new intent to go to {@link SevenWondersEditorActivity}
                Intent intent = new Intent(SevenWondersActivity.this, SevenWondersEditorActivity.class);

                /*
                 * form the content URI that represents the specific player that was clicked on, by
                 * appending the "id" (passed as input to this method) onto the
                 * {@link PlayerEntry#CONENT_URI}.
                 */
                Uri currentPlayerUri = ContentUris.withAppendedId(PlayerEntry.CONTENT_URI_SEVENWONDERS, id);

                // Set the URI on the data field of the intent
                intent.setData(currentPlayerUri);

                // Launch the {@link SevenWondersEditorActivity} to display the data for the current player
                startActivity(intent);
            }
        });

        // Kick off the loader
        getSupportLoaderManager().initLoader(PLAYER_LOADER, null, this);

    }

    /*
     * helper method to delete all players in the Seven Wonders database table
     */
    private void deleteAllPlayers() {
        int rowsDeleted = getContentResolver()
                .delete(PlayerEntry.CONTENT_URI_SEVENWONDERS, null, null);

        Log.v("SevenWondersActivity",
                rowsDeleted + " rows deleted from 7 Wonders player database");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*
         * Inflate the menu options from the res/menu/menu_catalog.xml file. This adds menu items
         * to the app bar.
         */
        //TODO: create menu_catalog.xml in the menu subfolder
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu.
        switch (item.getItemId()) {
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                deleteAllPlayers();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Define a projection that specifies the columns from the table we care about
        String[] projection = {
                PlayerEntry._ID,
                PlayerEntry.COLUMN_NAME,
                PlayerEntry.COLUMN_TOTAL};

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
