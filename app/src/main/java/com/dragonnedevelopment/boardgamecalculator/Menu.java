package com.dragonnedevelopment.boardgamecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import com.dragonnedevelopment.boardgamecalculator.model.Game;

import java.util.ArrayList;

public class Menu extends AppCompatActivity implements ImageDownloader.Callback{

    Intent gameIntent;

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
        ImageDownloader.downloadImage(this, Menu.this);
    }

    @Override
    public void onDone(ArrayList<Game> games) {
        /*
         * Create a {@link GameAdapter}, whose data source is a list of {@link Game}s. The adapter
         * knows how to create grid items for each item in the list.
         */
        GridView gridView = (GridView)findViewById(R.id.game_grid_view);
        GameMenuAdapter adapter = new GameMenuAdapter(this, R.layout.grid_item_layout, games);
        gridView.setAdapter(adapter);

        /*// Set a click listener on that View
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Game item = (Game) adapterView.getItemAtPosition(position);

                // set the intent to open the gameScoringActivity
                gameIntent = new Intent(Menu.this, *//* Activity class name *//*);
                String gameName = item.getGameName();
                gameIntent.putExtra(EXTRA_GAME_NAME, gameName);
                startActivity(gameIntent);
            }
        });*/
    }
}
