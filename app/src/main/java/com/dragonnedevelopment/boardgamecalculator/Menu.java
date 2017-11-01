package com.dragonnedevelopment.boardgamecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.dragonnedevelopment.boardgamecalculator.model.Game;

import java.util.ArrayList;
import java.util.Objects;

public class Menu extends AppCompatActivity implements ImageDownloader.Callback{

    Intent gameIntent;
    GridView gridView;

    public final static String EXTRA_GAME_NAME =
            "com.dragonnedevelopment.boardgamecalculator.EXTRA_GAME_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar menuToolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        setSupportActionBar(menuToolbar);
        getSupportActionBar().setTitle(getString(R.string.menu_title));
    }

    /**
     * Call ImageDownloader.downloadImage from onStart or onResume instead of onCreate to ensure
     * that the images aren't downloaded too early.
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (gridView == null) {
            ImageDownloader.downloadImage(this, Menu.this);
        }

    }

    @Override
    public void onDone(ArrayList<Game> games) {
        /*
         * Create a {@link GameAdapter}, whose data source is a list of {@link Game}s. The adapter
         * knows how to create grid items for each item in the list.
         */
        gridView = (GridView) findViewById(R.id.game_grid_view);
        GameMenuAdapter adapter = new GameMenuAdapter(this, R.layout.grid_item_layout, games);
        gridView.setAdapter(adapter);

        // Set a click listener on that View
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Game item = (Game) adapterView.getItemAtPosition(position);

                String gameName = item.getGameName();

                // set the intent to open the gameScoringActivity
                whichGame(gameName);
            }
        });
    }

    private void whichGame(String gameName) {
        if (isSevenWonders(gameName)) {
            gameIntent = new Intent(Menu.this, SevenWondersActivity.class);
            startCalculator(gameName);
        } else if (isAlhambra(gameName)) {
            gameIntent = new Intent(Menu.this, AlhambraActivity.class);
            startCalculator(gameName);
        } else if (isAscension(gameName)) {
            gameIntent = new Intent(Menu.this, AscensionActivity.class);
            startCalculator(gameName);
        } else if (isCaverna(gameName)) {
            gameIntent = new Intent(Menu.this, CavernaActivity.class);
            startCalculator(gameName);
        } else if (isEclipse(gameName)) {
            gameIntent = new Intent(Menu.this, EclipseActivity.class);
            startCalculator(gameName);
        } else if (isCatan(gameName)) {
            gameIntent = new Intent(Menu.this, CatanActivity.class);
            startCalculator(gameName);
        } else if (isTakenoko(gameName)) {
            gameIntent = new Intent(Menu.this, TakenokoActivity.class);
            startCalculator(gameName);
        } else if (isSinglePoint(gameName)) {
            gameIntent = new Intent(Menu.this, SinglePointActivity.class);
            startCalculator(gameName);
        }
    }

    private void startCalculator(String gameName) {
        gameIntent.putExtra(EXTRA_GAME_NAME, gameName);
        startActivity(gameIntent);
    }

    private boolean isSinglePoint(String gameName) {
        return Objects.equals(gameName, getString(R.string.singlepoint_game_name));
    }

    private boolean isTakenoko(String gameName) {
        return Objects.equals(gameName, getString(R.string.takenoko_game_name));
    }

    private boolean isCatan(String gameName) {
        return Objects.equals(gameName, getString(R.string.catan_game_name));
    }

    private boolean isEclipse(String gameName) {
        return Objects.equals(gameName, getString(R.string.eclipse_game_name));
    }

    private boolean isCaverna(String gameName) {
        return Objects.equals(gameName, getString(R.string.caverna_game_name));
    }

    private boolean isAscension(String gameName) {
        return Objects.equals(gameName, getString(R.string.ascension_game_name));
    }

    private boolean isAlhambra(String gameName) {
        return Objects.equals(gameName, getString(R.string.alhambra_game_name));
    }

    private boolean isSevenWonders(String gameName) {
        return Objects.equals(gameName, getString(R.string.sevenwonders_game_name));
    }
}
