package com.dragonnedevelopment.boardgamecalculator.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.dragonnedevelopment.boardgamecalculator.R;
import com.dragonnedevelopment.boardgamecalculator.data.GamesContract.PlayerEntry;

/**
 * BoardGameCalculator Created by Muir on 02/11/2017.
 * <p>
 * {@link SevenWondersCursorAdapter} is an adapter for a list view that uses a {@link Cursor} of
 * player data as its data source. This adapter knows how to create list items for each row of
 * player data in the {@link Cursor}.
 */

public class SevenWondersCursorAdapter extends CursorAdapter {

    /**
     * constructs a new {@link SevenWondersCursorAdapter}
     *
     * @param context the context
     * @param c       the cursor from which to get the data
     */
    public SevenWondersCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct position
     * @param parent  The parent to which the new view is attached to
     * @return The newly created list item view
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in seven_wonders_player_layout.xml
        return LayoutInflater.from(context)
                .inflate(R.layout.seven_wonders_player_layout, parent, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find individual views that we want to modify in the player layout
        // Player bar
        EditText nameTextView = (EditText) view.findViewById(R.id.playerSevenWonders);
        TextView scoreTotalTextView = (TextView) view.findViewById(R.id.scoreTotalSevenWonders);
        // Expandable score card views
        EditText militaryScore = (EditText) view.findViewById(R.id.militaryConflictsScore);
        EditText treasuryScore = (EditText) view.findViewById(R.id.treasuryContentsScore);
        EditText wondersScore = (EditText) view.findViewById(R.id.wondersScore);
        EditText civilianScore = (EditText) view.findViewById(R.id.civilianStructuresScore);
        TextView scientificScore = (TextView) view.findViewById(R.id.scientificStructuresScore);
        TextView identicalScienceScore = (TextView) view.findViewById(R.id.identicalScientificSymbolsScore);
        EditText protractorSets = (EditText) view.findViewById(R.id.numberOfProtractorSets);
        EditText tabletSets = (EditText) view.findViewById(R.id.numberOfTabletSets);
        EditText cogSets = (EditText) view.findViewById(R.id.numberOfCogSets);
        EditText differentScienceScore = (EditText) view.findViewById(R.id.differentScientificSymbolsScore);
        EditText commercialScore = (EditText) view.findViewById(R.id.commercialStructuresScore);
        EditText guildsScore = (EditText) view.findViewById(R.id.guildsScore);

        // find the columns of player attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_NAME);
        int totalScoreColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_TOTAL);
        int militaryScoreColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_MILITARY_CONFLICTS);
        int treasuryScoreColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_TREASURY);
        int wondersScoreColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_WONDER);
        int civilianScoreColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_CIVILIAN_STRUCTURES);
        int scientificScoreColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_SCIENTIFIC_STRUCTURES_TOTAL);
        int identicalScienceScoreColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_IDENTICAL_SCIENTIFIC_SETS);
        int protractorSetsColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_PROTRACTOR_SETS);
        int tabletSetsColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_TABLET_SETS);
        int cogSetsColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_COGS_SETS);
        int differentScienceScoreColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_DIFFERENT_SCIENTIFIC_SETS);
        int commercialScoreColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_COMMERCIAL_STRUCTURES);
        int guildsScoreColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_GUILDS);

        // Read the player attributes from the Cursor for the current player
        String playerName = cursor.getString(nameColumnIndex);
        String playerTotal = cursor.getString(totalScoreColumnIndex);
        String playerMilitaryScore = cursor.getString(militaryScoreColumnIndex);
        String playerTreasuryScore = cursor.getString(treasuryScoreColumnIndex);
        String playerWonderScore = cursor.getString(wondersScoreColumnIndex);
        String playerCivilianScore = cursor.getString(civilianScoreColumnIndex);
        String playerScientificTotal = cursor.getString(scientificScoreColumnIndex);
        String playerIdenticalSetsScoreTotal = cursor.getString(identicalScienceScoreColumnIndex);
        String playerProtractorSetsNumber = cursor.getString(protractorSetsColumnIndex);
        String playerTabletsSetsNumber = cursor.getString(tabletSetsColumnIndex);
        String playerCogsSetsNumber = cursor.getString(cogSetsColumnIndex);
        String playerDifferentScienceSetsNumber = cursor.getString(differentScienceScoreColumnIndex);
        String playerCommercialScore = cursor.getString(commercialScoreColumnIndex);
        String playerGuildScore = cursor.getString(guildsScoreColumnIndex);

        // Update the views with the attributes for the current player
        nameTextView.setText(playerName);
        scoreTotalTextView.setText(playerTotal);
        militaryScore.setText(playerMilitaryScore);
        treasuryScore.setText(playerTreasuryScore);
        wondersScore.setText(playerWonderScore);
        civilianScore.setText(playerCivilianScore);
        scientificScore.setText(playerScientificTotal);
        identicalScienceScore.setText(playerIdenticalSetsScoreTotal);
        protractorSets.setText(playerProtractorSetsNumber);
        tabletSets.setText(playerTabletsSetsNumber);
        cogSets.setText(playerCogsSetsNumber);
        differentScienceScore.setText(playerDifferentScienceSetsNumber);
        commercialScore.setText(playerCommercialScore);
        guildsScore.setText(playerGuildScore);

    }
}
