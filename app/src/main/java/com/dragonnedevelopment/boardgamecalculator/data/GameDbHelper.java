package com.dragonnedevelopment.boardgamecalculator.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dragonnedevelopment.boardgamecalculator.data.GamesContract.PlayerEntry;


/**
 * BoardGameCalculator Created by Muir on 30/10/2017.
 */

public class GameDbHelper extends SQLiteOpenHelper {

    // Name of the database file
    private static final String DATABASE_NAME = "games.db";

    // Database version. If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 1;

    private static final String INT_NOT_NULL = " INTEGER NOT NULL DEFAULT 0, ";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";

    // Create a string that contains the SQL statement to create the 7 Wonders table
    private String SQL_CREATE_SEVENWONERS_TABLE = "CREATE TABLE "
            + PlayerEntry.TABLE_SEVENWONDERS
            + " ("
            + PlayerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PlayerEntry.COLUMN_NAME + " TEXT NOT NULL, "
            + PlayerEntry.COLUMN_TOTAL + INT_NOT_NULL
            + PlayerEntry.COLUMN_MILITARY_CONFLICTS + INT_NOT_NULL
            + PlayerEntry.COLUMN_TREASURY + INT_NOT_NULL
            + PlayerEntry.COLUMN_WONDER + INT_NOT_NULL
            + PlayerEntry.COLUMN_CIVILIAN_STRUCTURES + INT_NOT_NULL
            + PlayerEntry.COLUMN_SCIENTIFIC_STRUCTURES_TOTAL + INT_NOT_NULL
            + PlayerEntry.COLUMN_IDENTICAL_SCIENTIFIC_SETS + INT_NOT_NULL
            + PlayerEntry.COLUMN_PROTRACTOR_SETS + INT_NOT_NULL
            + PlayerEntry.COLUMN_TABLET_SETS + INT_NOT_NULL
            + PlayerEntry.COLUMN_COGS_SETS + INT_NOT_NULL
            + PlayerEntry.COLUMN_DIFFERENT_SCIENTIFIC_SETS + INT_NOT_NULL
            + PlayerEntry.COLUMN_COMMERCIAL_STRUCTURES + INT_NOT_NULL
            + PlayerEntry.COLUMN_GUILDS + " INTEGER NOT NULL DEFAULT 0);";

    /**
     * Constructs a new instance of {@link GameDbHelper}
     *
     * @param context context of the app
     */
    public GameDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void CreateTable(GameType gameType, SQLiteDatabase database) {
        switch (gameType) {
            case SevenWonders:
                database.execSQL(SQL_CREATE_SEVENWONERS_TABLE);
                break;
            // TODO: add cases for all other games
            default:
                throw new IllegalArgumentException(" Create table not implemented for this game");
        }
    }

    // called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Execute the SQL statements
        sqLiteDatabase.execSQL(SQL_CREATE_SEVENWONERS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        sqLiteDatabase.execSQL(DROP_TABLE + PlayerEntry.TABLE_SEVENWONDERS);
        sqLiteDatabase.execSQL(DROP_TABLE + PlayerEntry.TABLE_ALHAMBRA);
        sqLiteDatabase.execSQL(DROP_TABLE + PlayerEntry.TABLE_ASCENSION);
        sqLiteDatabase.execSQL(DROP_TABLE + PlayerEntry.TABLE_CAVERNA);
        sqLiteDatabase.execSQL(DROP_TABLE + PlayerEntry.TABLE_ECLIPSE);
        sqLiteDatabase.execSQL(DROP_TABLE + PlayerEntry.TABLE_CATAN);
        sqLiteDatabase.execSQL(DROP_TABLE + PlayerEntry.TABLE_TAKENOKO);
        sqLiteDatabase.execSQL(DROP_TABLE + PlayerEntry.TABLE_SINGLEPOINT);

        // create new tables
        onCreate(sqLiteDatabase);

    }

    public enum GameType {
        SevenWonders,
        Alhambra,
        Ascension,
        Caverna,
        Eclipse,
        Catan,
        Takenoko,
        SinglePoint
    }
}
