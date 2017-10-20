package com.dragonnedevelopment.boardgamecalculator;

import android.content.Context;

import com.dragonnedevelopment.boardgamecalculator.model.Game;

import java.util.ArrayList;

/**
 * BoardGameCalculator Created by Muir on 20/10/2017.
 */


public class ImageDownloader {

    // Create an ArrayList of games.
    final static ArrayList<Game> games = new ArrayList<>();

    static void downloadImage(Context context) {

        // image resources
        int alhambraResourceId = R.drawable.alhambra;
        int ascensionResourceId = R.drawable.ascension;
        int catanResourceId = R.drawable.catan;
        int cavernaResourceId = R.drawable.cavera;
        int eclipseResourceId = R.drawable.eclipse;
        int sevenWondersResourceId = R.drawable.sevenwonders;
        int takenokoResourceId = R.drawable.takenoko;
        int plusMinusResourceId = R.drawable.plusminus;

        // Fill ArrayList with Game objects
        games.add(new Game(context.getString(R.string.alhambra_game_name), alhambraResourceId));
        games.add(new Game(context.getString(R.string.ascension_game_name), ascensionResourceId));
        games.add(new Game(context.getString(R.string.catan_game_name), catanResourceId));
        games.add(new Game(context.getString(R.string.caverna_game_name), cavernaResourceId));
        games.add(new Game(context.getString(R.string.eclipse_game_name), eclipseResourceId));
        games.add(new Game(context.getString(R.string.sevenwonders_game_name), sevenWondersResourceId));
        games.add(new Game(context.getString(R.string.takenoko_game_name), takenokoResourceId));
        games.add(new Game(context.getString(R.string.singlepoint_game_name), plusMinusResourceId));

    }

}
