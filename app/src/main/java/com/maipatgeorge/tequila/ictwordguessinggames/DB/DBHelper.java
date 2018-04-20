package com.maipatgeorge.tequila.ictwordguessinggames.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_CAT;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_FBID;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_F_ID;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_Gname;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_ID;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_LEVEL;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_L_ID;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_NAME;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_TOKEN;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_catID;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_Category;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_Fbuser;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_FbuserPass;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_Guest;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_GuestPass;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_Level;

public class DBHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DBHelper";

    // Database Version
    private static final int DB_VERSION = 23;

    // Database Name
    private static final String DB_NAME = "ICT_game.db";

    // Table Create Statements
    // GUEST table create statement
    private static final String CREATE_TABLE_GUEST = "CREATE TABLE "
            + TABLE_Guest + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT);";


    //FBUSER table create statement
    private static final String CREATE_TABLE_FB_USER = "CREATE TABLE "
            + TABLE_Fbuser + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_FBID +" TEXT, "
            + KEY_NAME+" TEXT,"+KEY_TOKEN+" TEXT)";


    //Category table create statement
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE "
            + TABLE_Category + "( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_CAT +" TEXT)";

    //LEVEL table create statement
    private static final String CREATE_TABLE_LEVEL = "CREATE TABLE "
            + TABLE_Level + " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_LEVEL +" TAXT, "
            + KEY_catID+" INTEGER)";

    //GuestPass table create statement
    private static final String CREATE_TABLE_GuestPass = "CREATE TABLE " + TABLE_GuestPass + "( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_Gname + " TEXT, " + KEY_L_ID +" INTEGER )";

    //FbuserPass table create statement
    private static final String CREATE_TABLE_FbuserPass = "CREATE TABLE " + TABLE_FbuserPass + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_F_ID+" TEXT, " + KEY_L_ID +" INTEGER )";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_GUEST);
        db.execSQL(CREATE_TABLE_FB_USER);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_LEVEL);
        db.execSQL(CREATE_TABLE_GuestPass);
        db.execSQL(CREATE_TABLE_FbuserPass);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Guest);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Fbuser);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Category);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Level);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GuestPass);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FbuserPass);

        // create new tables
        onCreate(db);
    }


}
