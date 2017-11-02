package com.dragonnedevelopment.boardgamecalculator.data;

import android.content.ContentResolver;
import android.net.Uri;
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

    /**
     * The "Content authority" is a name for the entire content provider, similar to the relationship
     * between a domain name and its website. A convenient string to use for the content authority
     * is the package name for the app, which is guaranteed to be unique on the device
     */
    static final String CONTENT_AUTHORITY = "com.dragonnedevelopment.boardgamecalculator";

    /**
     * Use CONTENT_AUTHORITY to crete the base of all URI's which apps will use to contact the
     * content provider.
     */
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible paths (appended to base content URI for possible URI's).
     */
    static final String PATH_SEVENWONDERS = "sevenwonders";
    static final String PATH_ALHAMBRA = "alhambra";
    static final String PATH_ASCENSION = "ascension";
    static final String PATH_CAVERNA = "caverna";
    static final String PATH_ECLIPSE = "eclipse";
    static final String PATH_CATAN = "catan";
    static final String PATH_TAKENOKO = "takenoko";
    static final String PATH_SINGLEPOINT = "singlepoint";

    /*
     * Inner class that defines constant values for the games database table. Each entry in the
     * table represents a single player
     */
    public static final class PlayerEntry implements BaseColumns {

        /**
         * the content URI to access the seven wonders player data in the provider.
         */
        public static final Uri CONTENT_URI_SEVENWONDERS =
                Uri.withAppendedPath(BASE_CONTENT_URI, PATH_SEVENWONDERS);

        /**
         * The content URI to access the alhambra player data in the provider.
         */
        public static final Uri CONTENT_URI_ALHAMBRA =
                Uri.withAppendedPath(BASE_CONTENT_URI, PATH_ALHAMBRA);

        /**
         * The content URI to access the ascension player data in the provider.
         */
        public static final Uri CONTENT_URI_ASCENSION =
                Uri.withAppendedPath(BASE_CONTENT_URI, PATH_ASCENSION);

        /**
         * The content URI to access the caverna player data in the provider.
         */
        public static final Uri CONTENT_URI_CAVERNA =
                Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CAVERNA);

        /**
         * The content URI to access the eclipse player data in the provider.
         */
        public static final Uri CONTENT_URI_ECLIPSE =
                Uri.withAppendedPath(BASE_CONTENT_URI, PATH_ECLIPSE);

        /**
         * The content URI to access the catan player data in the provider.
         */
        public static final Uri CONTENT_URI_CATAN =
                Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CATAN);

        /**
         * The content URI to access the takenoko player data in the provider.
         */
        public static final Uri CONTENT_URI_TAKENOKO =
                Uri.withAppendedPath(BASE_CONTENT_URI, PATH_TAKENOKO);

        /**
         * The content URI to access the single point games player data in the provider.
         */
        public static final Uri CONTENT_URI_SINGLEPOINT =
                Uri.withAppendedPath(BASE_CONTENT_URI, PATH_SINGLEPOINT);

        /**
         * The MIME type of the {@link #CONTENT_URI_SEVENWONDERS} for a list of players
         */
        static final String CONTENT_LIST_TYPE_SEVENWONDERS =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SEVENWONDERS;

        /**
         * The MIME type of the {@link #CONTENT_URI_ALHAMBRA} for a list of players
         */
        static final String CONTENT_LIST_TYPE_ALHAMBRA =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ALHAMBRA;

        /**
         * The MIME type of the {@link #CONTENT_URI_ASCENSION} for a list of players
         */
        static final String CONTENT_LIST_TYPE_ASCENSION =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ASCENSION;

        /**
         * The MIME type of the {@link #CONTENT_URI_CAVERNA} for a list of players
         */
        static final String CONTENT_LIST_TYPE_CAVERNA =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CAVERNA;

        /**
         * The MIME type of the {@link #CONTENT_URI_ECLIPSE} for a list of players
         */
        static final String CONTENT_LIST_TYPE_ECLIPSE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ECLIPSE;

        /**
         * The MIME type of the {@link #CONTENT_URI_CATAN} for a list of players
         */
        static final String CONTENT_LIST_TYPE_CATAN =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CATAN;

        /**
         * The MIME type of the {@link #CONTENT_URI_TAKENOKO} for a list of players
         */
        static final String CONTENT_LIST_TYPE_TAKENOKO =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TAKENOKO;

        /**
         * The MIME type of the {@link #CONTENT_URI_SINGLEPOINT} for a list of players
         */
        static final String CONTENT_LIST_TYPE_SINGLEPOINT =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SINGLEPOINT;

        /**
         * the MIME type of the {@link #CONTENT_URI_SEVENWONDERS} for a single player
         */
        static final String CONTENT_ITEM_TYPE_SEVENWONDERS =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SEVENWONDERS;

        /**
         * the MIME type of the {@link #CONTENT_URI_ALHAMBRA} for a single player
         */
        static final String CONTENT_ITEM_TYPE_ALHAMBRA =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ALHAMBRA;

        /**
         * the MIME type of the {@link #CONTENT_URI_ASCENSION} for a single player
         */
        static final String CONTENT_ITEM_TYPE_ASCENSION =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ASCENSION;

        /**
         * the MIME type of the {@link #CONTENT_URI_CAVERNA} for a single player
         */
        static final String CONTENT_ITEM_TYPE_CAVERNA =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CAVERNA;

        /**
         * the MIME type of the {@link #CONTENT_URI_ECLIPSE} for a single player
         */
        static final String CONTENT_ITEM_TYPE_ECLIPSE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ECLIPSE;

        /**
         * the MIME type of the {@link #CONTENT_URI_CATAN} for a single player
         */
        static final String CONTENT_ITEM_TYPE_CATAN =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CATAN;

        /**
         * the MIME type of the {@link #CONTENT_URI_TAKENOKO} for a single player
         */
        static final String CONTENT_ITEM_TYPE_TAKENOKO =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TAKENOKO;

        /**
         * the MIME type of the {@link #CONTENT_URI_SINGLEPOINT} for a single player
         */
        static final String CONTENT_ITEM_TYPE_SINGLEPOINT =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SINGLEPOINT;

        // names of database tables
        final static String TABLE_SEVENWONDERS = "sevenwonders";
        final static String TABLE_ALHAMBRA = "alhambra";
        final static String TABLE_ASCENSION = "ascension";
        final static String TABLE_CAVERNA = "caverna";
        final static String TABLE_ECLIPSE = "eclipse";
        final static String TABLE_CATAN = "catan";
        final static String TABLE_TAKENOKO = "takenoko";
        final static String TABLE_SINGLEPOINT = "singlepoint";

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
