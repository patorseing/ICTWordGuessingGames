package com.maipatgeorge.tequila.ictwordguessinggames.DB;

import android.provider.BaseColumns;

public class Constant implements BaseColumns {
    // Table Names
    public static final String TABLE_Guest = "Guest";
    public static final String TABLE_Fbuser = "Fbuser";
    public static final String TABLE_Category = "Category";
    public static final String TABLE_Level = "Level";
    public static final String TABLE_GuestPass = "GuestPass";
    public static final String TABLE_FbuserPass = "FbuserPass";

    // Common column names
    public static final String KEY_ID = "id";

    //GUEST & FBUSER
    public static final String KEY_NAME = "name";

    //FBUSER
    public static final String KEY_FBID = "fbid";
    public static final String KEY_TOKEN = "token";

    //Category
    public static final String KEY_CAT = "cat";
    public static String[] cat_name = new String[]{"wireless", "security", "database"};

    //Level
    public static final String KEY_LEVEL = "Q";
    public static final String KEY_catID = "catID";

    //GuestPass
    public static final String KEY_G_ID = "g_id";
    public static final String KEY_L_ID = "l_id";

    //FbuserPass
    public static final String KEY_F_ID = "f_id";
}
