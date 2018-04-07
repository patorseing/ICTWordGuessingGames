package com.maipatgeorge.tequila.ictwordguessinggames.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_ID;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_NAME;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_Guest;

public class DBHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DBHelper";

    // Database Version
    private static final int DB_VERSION = 1;

    // Database Name
    private static final String DB_NAME = "ICT_game.db";

    // Table Create Statements
    // GUEST table create statement
    private static final String CREATE_TABLE_GUEST = "CREATE TABLE "
            + TABLE_Guest + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT);";

    /*
    //FBUSER table create statement
    private static final String CREATE_TABLE_FB_USER = "CREATE TABLE "
            + TABLE_Fbuser + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FBID +"INTEGER, "
            + KEY_NAME+" TEXT)";


    //Category table create statement
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE "
            + TABLE_Category + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_CAT +" TEXT)";

    //LEVEL table create statement
    private static final String CREATE_TABLE_LEVEL = "CREATE TABLE "
            + TABLE_Level + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_LEVEL +" INTEGER, FOREIGN KEY ("
            + KEY_catID+") REFERENCES " +TABLE_Category+" ("+KEY_ID+"))";

    //GuestPass table create statement
    private static final String CREATE_TABLE_GuestPass = "CREATE TABLE "
            + TABLE_GuestPass + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, FOREIGN KEY ("
            + KEY_G_ID+") REFERENCES " +TABLE_Guest+" ("+KEY_ID+"), FOREIGN KEY ("
            + KEY_L_ID +") REFERENCES " +TABLE_Level+" ("+KEY_ID+"))";

    //FbuserPass table create statement
    private static final String CREATE_TABLE_FbuserPass = "CREATE TABLE "
            + TABLE_FbuserPass + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, FOREIGN KEY ("
            + KEY_F_ID+") REFERENCES " +TABLE_Fbuser+" ("+KEY_ID+"), FOREIGN KEY ("
            + KEY_L_ID +") REFERENCES " +TABLE_Level+" ("+KEY_ID+"))";
     */

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_GUEST);
        //db.execSQL(CREATE_TABLE_FB_USER);
        //db.execSQL(CREATE_TABLE_CATEGORY);
        //db.execSQL(CREATE_TABLE_LEVEL);
        //db.execSQL(CREATE_TABLE_FbuserPass);
        //db.execSQL(CREATE_TABLE_GuestPass);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Guest);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_Fbuser);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_Category);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_Level);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_FbuserPass);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_GuestPass);

        // create new tables
        onCreate(db);
    }
}
