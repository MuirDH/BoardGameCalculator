package com.dragonnedevelopment.boardgamecalculator.data;

import android.provider.BaseColumns;

/**
 * BoardGameCalculator Created by Muir on 30/10/2017.
 */

public class GamesContract {

    /*
     * To prevent someone from accidentally instantiating the contract class, give it an empty
     * constructor.
     */
    private GamesContract() {
    }

    /*
     * Inner class that defines constant values for the games database table. Each entry in the
     * table represents a single player
     */
    public static final class PlayerEntry implements BaseColumns {

        // names of database tables
        public final static String TABLE_SEVENWONDERS = "sevenwonders";
        public final static String TABLE_ALHAMBRA = "alhambra";
        public final static String TABLE_ASCENSION = "ascension";
        public final static String TABLE_CAVERNA = "caverna";
        public final static String TABLE_ECLIPSE = "eclipse";
        public final static String TABLE_CATAN = "catan";
        public final static String TABLE_TAKENOKO = "takenoko";
        public final static String TABLE_SINGLEPOINT = "singlepoint";

        // Common Column Names
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_TOTAL = "total";
        public final static String _ID = BaseColumns._ID;

        // 7 Wonders Table - Column Names
        public final static String COLUMN_MILITARY_CONFLICTS = "military_conflicts";
        public final static String COLUMN_TREASURY = "treasury";
        public final static String COLUMN_WONDER = "wonder";
        public final static String COLUMN_CIVILIAN_STRUCTURES = "civilian_structures";
        public final static String COLUMN_SCIENTIFIC_STRUCTURES_TOTAL = "scientific_total";
        public final static String COLUMN_IDENTICAL_SCIENTIFIC_SETS = "identical_science_sets";
        public final static String COLUMN_PROTRACTOR_SETS = "protractor_science_sets";
        public final static String COLUMN_TABLET_SETS = "tablet_science_sets";
        public final static String COLUMN_COGS_SETS = "cogs_science_sets";
        public final static String COLUMN_DIFFERENT_SCIENTIFIC_SETS = "different_science_sets";
        public final static String COLUMN_COMMERCIAL_STRUCTURES = "commercial_structures";
        public final static String COLUMN_GUILDS = "guilds";
    }
}
