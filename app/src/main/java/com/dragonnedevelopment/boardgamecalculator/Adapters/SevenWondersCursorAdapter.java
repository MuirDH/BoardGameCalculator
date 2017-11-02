package com.dragonnedevelopment.boardgamecalculator.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
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
        // Inflate a list item view using the layout specified in seven_wonders_player_summary.xml
        return LayoutInflater.from(context)
                .inflate(R.layout.seven_wonders_player_summary, parent, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find individual views that we want to modify in the player layout
        // Player bar
        TextView nameTextView = (TextView) view.findViewById(R.id.playerSevenWonders);
        TextView scoreTotalTextView = (TextView) view.findViewById(R.id.scoreTotalSevenWonders);


        // find the columns of player attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_NAME);
        int totalScoreColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_TOTAL);


        // Read the player attributes from the Cursor for the current player
        String playerName = cursor.getString(nameColumnIndex);
        String playerTotal = cursor.getString(totalScoreColumnIndex);

        if (TextUtils.isEmpty(playerName))
            playerName = context.getString(R.string.unknown_player_name);

        // Update the views with the attributes for the current player
        nameTextView.setText(playerName);
        scoreTotalTextView.setText(playerTotal);

    }
}
