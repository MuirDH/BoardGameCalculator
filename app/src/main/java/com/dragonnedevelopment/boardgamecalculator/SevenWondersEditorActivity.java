package com.dragonnedevelopment.boardgamecalculator;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dragonnedevelopment.boardgamecalculator.data.GamesContract.PlayerEntry;

/**
 * BoardGameCalculator Created by Muir on 02/11/2017.
 * <p>
 * allows user to create a new player or edit an existing one
 */

public class SevenWondersEditorActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    // Identifier for the player data loader
    private static final int EXISTING_PLAYER_LOADER = 0;

    // content URI for the existing player (null if it's a new player)
    private Uri currentPlayerUri;

    // EditText field to enter the player's name
    private EditText playerNameEditText;

    //TextView field which stores the player's total score
    private TextView playerScoreText;

    // EditText field to enter the military conflict points
    private EditText playerMilitaryPoints;

    // EditText field to enter the treasury contents points
    private EditText playerTreasuryPoints;

    // EditText field to enter the wonder points
    private EditText playerWonderPoints;

    // EditText field to enter the civilian structures points
    private EditText playerCivilianPoints;

    // TextView field to enter the total scientific points
    private TextView playerScienceTotalPoints;

    // EditText field to enter the number of protractors (science)
    private EditText playerProtractorsAmt;

    // EditText field to enter the number of tablets (science)
    private EditText playerTabletsAmt;

    // EditText field to enter the number of cogs (science)
    private EditText playerCogsAmt;

    // EditText field to enter the number of groups of 3 different symbols (science)
    private EditText playerDifferentSymbols;

    // EditText field to enter the commercial structures points
    private EditText playerCommercialPoints;

    // EditText field to enter the guilds points
    private EditText playerGuildsPoints;


    // Boolean flag that keeps track of whether the pet has been edited (true) or not (false)
    private boolean playerHasChanged = false;

    /*
     * OnTouchListener that listens for any user touches on a View, implying that they are modifying
     * the view, and we change the playerHasChanged boolean to true.
     */
    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            playerHasChanged = true;
            return false;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seven_wonders_player_layout);

        /*
         * Examine the intent that was used to launch this activity, in order to figure out if we're
         * creating a new player or editing an existing one.
         */
        isNewPlayer();

        // Find all relevant views that we will need to read user input from
        playerNameEditText = (EditText) findViewById(R.id.playerSevenWonders);
        playerScoreText = (TextView) findViewById(R.id.scoreTotalSevenWonders);
        playerMilitaryPoints = (EditText) findViewById(R.id.militaryConflictsScore);
        playerTreasuryPoints = (EditText) findViewById(R.id.treasuryContentsScore);
        playerWonderPoints = (EditText) findViewById(R.id.wondersScore);
        playerCivilianPoints = (EditText) findViewById(R.id.civilianStructuresScore);
        playerScienceTotalPoints = (TextView) findViewById(R.id.scientificStructuresScore);
        playerProtractorsAmt = (EditText) findViewById(R.id.numberOfProtractorSets);
        playerTabletsAmt = (EditText) findViewById(R.id.numberOfTabletSets);
        playerCogsAmt = (EditText) findViewById(R.id.numberOfCogSets);
        playerDifferentSymbols = (EditText) findViewById(R.id.differentScientificSymbolsScore);
        playerCommercialPoints = (EditText) findViewById(R.id.commercialStructuresScore);
        playerGuildsPoints = (EditText) findViewById(R.id.guildsScore);

        /*
         * Set up OnTouchListeners on all the input fields, so we can determine if the user has
         * touched or modified them. this will let us know if there are unsaved changes or not, if
         * the user tries to leave the editor without saving
         */
        playerNameEditText.setOnTouchListener(touchListener);
        playerMilitaryPoints.setOnTouchListener(touchListener);
        playerTreasuryPoints.setOnTouchListener(touchListener);
        playerWonderPoints.setOnTouchListener(touchListener);
        playerCivilianPoints.setOnTouchListener(touchListener);
        playerProtractorsAmt.setOnTouchListener(touchListener);
        playerTabletsAmt.setOnTouchListener(touchListener);
        playerCogsAmt.setOnTouchListener(touchListener);
        playerDifferentSymbols.setOnTouchListener(touchListener);
        playerCommercialPoints.setOnTouchListener(touchListener);
        playerGuildsPoints.setOnTouchListener(touchListener);

    }

    private void isNewPlayer() {

        Intent intent = getIntent();
        currentPlayerUri = intent.getData();

        /*
         * If the intent DOES NOT contain a player content URI, then we know that we're creating a
         * new player.
         */
        if (currentPlayerUri == null) {
            // this is a new player, so change the app bar to say "Add a player"
            setTitle(getString(R.string.editor_activity_title_new_player));

        /*
         * Invalidate the options menu, so the "Delete" menu option can be hidden. (It doesn't make
         * sense to delete a player that hasn't been created yet
         */
            invalidateOptionsMenu();
        } else {
            // Otherwise this is an existing player, so change the app bar to say "Edit player scores"
            setTitle(getString(R.string.editor_activity_title_edit_player));

            /*
             * Initalise a loader to read the player data from the database and display the current
             * values in the editor
             */
            getLoaderManager().initLoader(EXISTING_PLAYER_LOADER, null, this);
        }
    }

    /**
     * Get user input from editor and save new player into database
     */
    private void savePlayer() {
        /*
         * Read from input fields.
         * Use trim to eliminate leading or trailing white space
         */
        String nameString = playerNameEditText.getText().toString().trim();
        String scoreString = playerScoreText.getText().toString().trim();
        String militaryString = playerMilitaryPoints.getText().toString().trim();
        String treasuryString = playerTreasuryPoints.getText().toString().trim();
        String wonderString = playerWonderPoints.getText().toString().trim();
        String civilianString = playerCivilianPoints.getText().toString().trim();
        String scienceString = playerScienceTotalPoints.getText().toString().trim();
        String protractorsString = playerProtractorsAmt.getText().toString().trim();
        String tabletsString = playerTabletsAmt.getText().toString().trim();
        String cogsString = playerCogsAmt.getText().toString().trim();
        String differentSymbolsString = playerDifferentSymbols.getText().toString().trim();
        String commercialString = playerCommercialPoints.getText().toString().trim();
        String guildsString = playerGuildsPoints.getText().toString().trim();

        if (blankFields(nameString, militaryString, treasuryString, wonderString, civilianString,
                protractorsString, tabletsString, cogsString, differentSymbolsString,
                commercialString, guildsString)) return;

        /*
         * Create a ContentValues object where column names are the keys, and player attributes
         * from the editor are the values
         */
        ContentValues values = new ContentValues();
        values.put(PlayerEntry.COLUMN_NAME, nameString);

        /*
         * If the numeric values are not provided by the user, don't try to parse the string into
         * an integer value. Use 0 by default
         */

        int score = 0;
        int military = 0;
        int treasury = 0;
        int wonder = 0;
        int civilian = 0;
        int science = 0;
        int protractors = 0;
        int tablets = 0;
        int cogs = 0;
        int diffScienceSets = 0;
        int commercial = 0;
        int guilds = 0;

        if (!TextUtils.isEmpty(scoreString)) {
            score = Integer.parseInt(scoreString);
        }
        values.put(PlayerEntry.COLUMN_TOTAL, scoreString);

        if (!TextUtils.isEmpty(militaryString)) {
            military = Integer.parseInt(militaryString);
        }
        values.put(PlayerEntry.COLUMN_MILITARY_CONFLICTS, militaryString);

        if (!TextUtils.isEmpty(treasuryString)) {
            treasury = Integer.parseInt(treasuryString);
        }
        values.put(PlayerEntry.COLUMN_TREASURY, treasuryString);

        if (!TextUtils.isEmpty(wonderString)) {
            wonder = Integer.parseInt(wonderString);
        }
        values.put(PlayerEntry.COLUMN_WONDER, wonderString);

        if (!TextUtils.isEmpty(civilianString)) {
            civilian = Integer.parseInt(civilianString);
        }
        values.put(PlayerEntry.COLUMN_CIVILIAN_STRUCTURES, civilianString);

        if (!TextUtils.isEmpty(scienceString)) {
            science = Integer.parseInt(scienceString);
        }
        values.put(PlayerEntry.COLUMN_SCIENTIFIC_STRUCTURES_TOTAL, scienceString);

        if (!TextUtils.isEmpty(protractorsString)) {
            protractors = Integer.parseInt(protractorsString);
        }
        values.put(PlayerEntry.COLUMN_PROTRACTOR_SETS, protractorsString);

        if (!TextUtils.isEmpty(tabletsString)) {
            tablets = Integer.parseInt(tabletsString);
        }
        values.put(PlayerEntry.COLUMN_TABLET_SETS, tabletsString);

        if (!TextUtils.isEmpty(cogsString)) {
            cogs = Integer.parseInt(cogsString);
        }
        values.put(PlayerEntry.COLUMN_COGS_SETS, cogsString);

        if (!TextUtils.isEmpty(differentSymbolsString)) {
            diffScienceSets = Integer.parseInt(differentSymbolsString);
        }
        values.put(PlayerEntry.COLUMN_DIFFERENT_SCIENTIFIC_SETS, differentSymbolsString);

        if (!TextUtils.isEmpty(commercialString)) {
            commercial = Integer.parseInt(commercialString);
        }
        values.put(PlayerEntry.COLUMN_COMMERCIAL_STRUCTURES, commercialString);

        if (!TextUtils.isEmpty(guildsString)) {
            guilds = Integer.parseInt(guildsString);
        }
        values.put(PlayerEntry.COLUMN_GUILDS, guildsString);

        // Determine if this is a new or existing player by checking if currentPlayerUri is null
        newOrExistingPlayer(values);

    }

    private void newOrExistingPlayer(ContentValues values) {
        if (currentPlayerUri == null) {
            // Insert a new player into the provider, returning the content URI for the new player
            Uri newUri = getContentResolver().insert(PlayerEntry.CONTENT_URI_SEVENWONDERS, values);

            // Show a toast message depending on whether or not the insertion was successful
            if (newUri == null) {
                // If the new content URI is null, there was an error
                Toast.makeText(this, R.string.insert_player_failed, Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the insertion was successful and we can display a toast
                Toast.makeText(this, getString(R.string.insert_player_successful), Toast.LENGTH_SHORT).show();
            }
        } else {
            /*
             * Otherwise this is an existing player, so update the player with content URI:
             * currentPlayerUri and pass in the new ContentValues. Pass in null for the selection
             * and selection args because currentPlayerUri will already identify the correct row in
             * the database that we want to modify
             */
            int rowsAffected = getContentResolver().update(currentPlayerUri, values, null, null);

            // Show a toast message depending on whether or not the update was successful
            if (rowsAffected == 0) {
                // If no rows were affected, then there was an error with the update
                Toast.makeText(this, R.string.insert_player_failed, Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the update was successful and we can display a toast
                Toast.makeText(this, getString(R.string.insert_player_successful), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean blankFields(String nameString, String militaryString, String treasuryString,
                                String wonderString, String civilianString, String protractorsString,
                                String tabletsString, String cogsString,
                                String differentSymbolsString, String commercialString,
                                String guildsString) {
        /*
         * check if this is supposed to be a new player and check if all the fields in the editor
         * are blank
         */
        return currentPlayerUri == null && TextUtils.isEmpty(nameString)
                && TextUtils.isEmpty(militaryString) && TextUtils.isEmpty(treasuryString)
                && TextUtils.isEmpty(wonderString) && TextUtils.isEmpty(civilianString)
                && TextUtils.isEmpty(protractorsString) && TextUtils.isEmpty(tabletsString)
                && TextUtils.isEmpty(cogsString) && TextUtils.isEmpty(differentSymbolsString)
                && TextUtils.isEmpty(commercialString) && TextUtils.isEmpty(guildsString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*
         * Inflate the menu options form the res/menu/menu_editor.xml file. This adds menu items
         * to the app bar.
         */
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        // If this is a new player, hide the "Delete" menu item.
        if (currentPlayerUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option

            case R.id.action_save:
                //Save player to database
                savePlayer();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete menu option
            case R.id.action_delete:
                //Pop up confirmation dialog for deletion
                showDeleteConfirmationDialog();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                /*
                 * If the player hasn't changed, continue with navigating up to parent activity
                 * which is the {@link SevenWondersActivity}.
                 */
                if (!playerHasChanged) {
                    NavUtils.navigateUpFromSameTask(SevenWondersEditorActivity.this);
                    return true;
                }

                /*
                 * Otherwise if there are unsaved changes, setup a dialog to warn the user. Create
                 * a click listener to handle the user confirming that changes should be discarded.
                 */
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity
                                NavUtils.navigateUpFromSameTask(SevenWondersEditorActivity.this);
                            }
                        };

                // show a dialog that notifies the user they have unsaved changes
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // This method is called when the back button is pressed
    @Override
    public void onBackPressed() {
        // if the player hasn't changed, continue with handling back button press
        if (!playerHasChanged) {
            super.onBackPressed();
            return;
        }

        /*
         * otherwise if there are unsaved changes, setup a dialog to warn the user. Create a click
         * listener to handle the user confirming that changes should be discarded
         */
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // user clicked "Discard" button, close the current activity
                        finish();
                    }
                };

        // show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }


    private void showUnsavedChangesDialog(DialogInterface.OnClickListener discardButtonClickListener) {
        /*
         * Create an AlertDialog.Builder and set the message and click listeners for the positive
         * and negative buttons on the dialog.
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                /*
                 * User clicked the "Keep Editing" button, so dismiss the dialog and continue
                 * editing the player.
                 */
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        // create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showDeleteConfirmationDialog() {
        /*
         * Create an AlertDialog.Builder and set the message and click listeners for the positive
         * and negative buttons on the dialog
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the player
                deletePlayer();
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                /*
                 * User clicked the "Cancel" button, so dismiss the dialog and continue editing the
                 * player.
                 */
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // Perform the deletion of the Player in the database
    private void deletePlayer() {
        // Only perform the delete if this is an existing player.
        if (currentPlayerUri != null) {
            /*
             * Call the ContentResolver to delete the player at the given content URI. Pass in null
             * for the selection and selection args because the currentPlayerUri content URI already
             * identifies the player that we want.
             */
            int rowsDeleted = getContentResolver().delete(currentPlayerUri, null, null);

            // show a toast message depending on whether or not the delete was successful
            if (rowsDeleted == 0) {
                // if no rows were delted, then there was an error with the delete
                Toast.makeText(this, getString(R.string.editor_delete_player_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful.
                Toast.makeText(this, getString(R.string.editor_delete_player_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }

        // close the activity
        finish();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        /*
         * since the editor shows all player attributes, define a projection that contains all
         * columns from the player table
         */
        String[] projection = {
                PlayerEntry._ID,
                PlayerEntry.COLUMN_NAME,
                PlayerEntry.COLUMN_TOTAL,
                PlayerEntry.COLUMN_MILITARY_CONFLICTS,
                PlayerEntry.COLUMN_TREASURY,
                PlayerEntry.COLUMN_WONDER,
                PlayerEntry.COLUMN_CIVILIAN_STRUCTURES,
                PlayerEntry.COLUMN_SCIENTIFIC_STRUCTURES_TOTAL,
                PlayerEntry.COLUMN_PROTRACTOR_SETS,
                PlayerEntry.COLUMN_TABLET_SETS,
                PlayerEntry.COLUMN_COGS_SETS,
                PlayerEntry.COLUMN_DIFFERENT_SCIENTIFIC_SETS,
                PlayerEntry.COLUMN_COMMERCIAL_STRUCTURES,
                PlayerEntry.COLUMN_GUILDS};

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(
                this,    // Parent activity context
                currentPlayerUri, // Query the content URI for the current player
                projection, // columns to include in the resulting Cursor
                null, // No selection clause
                null, // no selection arguments
                null);  // default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Bail early if the cursor is null or there is less than 1 row in the cursor
        if (cursor == null || cursor.getCount() < 1)
            return;

        /*
         * Proceed with moving to the first row of the cursor and reading data from it. (this should
         * be the only row in the cursor)
         */
        if (cursor.moveToFirst()) {
            // Find the columns of player attributes that we're interested in.
            int playerNameColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_NAME);
            int playerTotalScoreColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_TOTAL);
            int playerMilitaryColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_MILITARY_CONFLICTS);
            int playerTreasuryColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_TREASURY);
            int playerWonderColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_WONDER);
            int playerCivilianColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_CIVILIAN_STRUCTURES);
            int playerScientificTotalColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_SCIENTIFIC_STRUCTURES_TOTAL);
            int playerProtractorSetsColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_PROTRACTOR_SETS);
            int playerTabletSetsColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_TABLET_SETS);
            int playerCogsSetsColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_COGS_SETS);
            int playerDifferentScienceSetsColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_DIFFERENT_SCIENTIFIC_SETS);
            int playerCommercialColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_COMMERCIAL_STRUCTURES);
            int playerGuildsColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_GUILDS);

            // Extract out the value from the Cursor for the given column index
            String playerName = cursor.getString(playerNameColumnIndex);
            int playerTotal = cursor.getInt(playerTotalScoreColumnIndex);
            int playerMilitary = cursor.getInt(playerMilitaryColumnIndex);
            int playerTreasury = cursor.getInt(playerTreasuryColumnIndex);
            int playerWonder = cursor.getInt(playerWonderColumnIndex);
            int playerCivilian = cursor.getInt(playerCivilianColumnIndex);
            int playerScienceTotal = cursor.getInt(playerScientificTotalColumnIndex);
            int playerProtractors = cursor.getInt(playerProtractorSetsColumnIndex);
            int playerTablets = cursor.getInt(playerTabletSetsColumnIndex);
            int playerCogs = cursor.getInt(playerCogsSetsColumnIndex);
            int playerDifferentScience = cursor.getInt(playerDifferentScienceSetsColumnIndex);
            int playerCommercial = cursor.getInt(playerCommercialColumnIndex);
            int playerGuilds = cursor.getInt(playerGuildsColumnIndex);

            // Update the views on the screen with the values from the database
            playerNameEditText.setText(playerName);
            playerScoreText.setText(Integer.toString(playerTotal));
            playerMilitaryPoints.setText(Integer.toString(playerMilitary));
            playerTreasuryPoints.setText(Integer.toString(playerTreasury));
            playerWonderPoints.setText(Integer.toString(playerWonder));
            playerCivilianPoints.setText(Integer.toString(playerCivilian));
            playerScienceTotalPoints.setText(Integer.toString(playerScienceTotal));
            playerProtractorsAmt.setText(Integer.toString(playerProtractors));
            playerTabletsAmt.setText(Integer.toString(playerTablets));
            playerCogsAmt.setText(Integer.toString(playerCogs));
            playerDifferentSymbols.setText(Integer.toString(playerDifferentScience));
            playerCommercialPoints.setText(Integer.toString(playerCommercial));
            playerGuildsPoints.setText(Integer.toString(playerGuilds));


        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // if the loader is invalidated, clear out all the data from the input fields.
        playerNameEditText.setText("");
        playerScoreText.setText("");
        playerMilitaryPoints.setText("");
        playerTreasuryPoints.setText("");
        playerWonderPoints.setText("");
        playerCivilianPoints.setText("");
        playerScienceTotalPoints.setText("");
        playerProtractorsAmt.setText("");
        playerTabletsAmt.setText("");
        playerCogsAmt.setText("");
        playerDifferentSymbols.setText("");
        playerCommercialPoints.setText("");
        playerGuildsPoints.setText("");

    }
}
