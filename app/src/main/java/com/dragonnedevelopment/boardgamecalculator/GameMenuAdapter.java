package com.dragonnedevelopment.boardgamecalculator;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dragonnedevelopment.boardgamecalculator.model.Game;

import java.util.ArrayList;

/**
 * BoardGameCalculator Created by Muir on 20/10/2017.
 */

/**
 * GameMenuAdapter is backed by an ArrayList of {@link Game} objects which populate the GridView in
 * Menu
 */

public class GameMenuAdapter extends ArrayAdapter<Game> {

    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public GameMenuAdapter(Context context, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }

    @Override
    // Create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder = null;
        Game currentGame = getItem(position);

        if (convertView == null) {
            // If it's not recycled, initialise some attributes
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) convertView.findViewById(R.id.game_grid_name);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageTitle.setText(currentGame.getGameName());
        holder.image.setImageResource(currentGame.getImageResourceId());
        return convertView;
    }
}
