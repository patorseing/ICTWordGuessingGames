package com.maipatgeorge.tequila.ictwordguessinggames;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.maipatgeorge.tequila.ictwordguessinggames.DB.DBHelper;
import com.maipatgeorge.tequila.ictwordguessinggames.freg.RenameDialogsFreg;
import com.maipatgeorge.tequila.ictwordguessinggames.freg.SettingDialogsFreg;

import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_CAT;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_Gname;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_Category;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_GuestPass;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.cat_name;

//*******************************************************************
// FBuserStartGame
//
// After user check in, this class like controlling section
// for user csn choose the part that they want.
//
//*******************************************************************

public class GuestStartGame extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView imageView;
    NavigationView navigationView;
    public TextView name;
    DBHelper helper;
    Intent intent;
    Bundle bd;

    Button wl_guest;
    Button se_guest;
    Button db_guest;
    String getName;

    MediaPlayer mysong;
    String start;
    int volume;
    int pos;
    float reduce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this activity uses navigation drawer activity
        super.onCreate(savedInstanceState);
        //set up the navigation
        setContentView(R.layout.activity_guest_start_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        imageView = navigationView.getHeaderView(0).findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.profile_image_web);
        name = navigationView.getHeaderView(0).findViewById(R.id.guestname);
        helper = new DBHelper(this);

        intent = getIntent();
        bd = intent.getExtras();

        if (savedInstanceState == null){
            start = intent.getStringExtra("start");
            volume = intent.getIntExtra("volume", 0);
            pos = intent.getIntExtra("pos", 0);
            bd = intent.getExtras();
            if(bd != null)
            {
                getName = (String) bd.get("name");
                name.setText(getName);
            }
        } else {
            start = savedInstanceState.getString("start");
            volume = savedInstanceState.getInt("volume");
            pos = savedInstanceState.getInt("pos");
            getName = savedInstanceState.getString("name");
            name.setText(getName);
        }

        reduce=(float)(100 - volume)/100;

        mysong = MediaPlayer.create(GuestStartGame.this, R.raw.feelingsohappy);
        mysong.start();
        mysong.setVolume(1-reduce, 1-reduce);
        mysong.setLooping(true);

        if (!start.equals("true")){
            mysong.stop();
        }
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SQLiteDatabase db = helper.getWritableDatabase();

        String catcount = "SELECT * FROM "+TABLE_Category+" WHERE "+KEY_CAT+" = ? ";

        SQLiteDatabase db1 = helper.getReadableDatabase();

        for (int i = 0; i<cat_name.length;i++){
            Cursor cursor = db1.rawQuery(catcount, new String[] {cat_name[i]});
            if (cursor.getCount() > 0){

            } else {
                ContentValues values = new ContentValues();
                values.put(KEY_CAT, cat_name[i]);
                db.insertOrThrow(TABLE_Category, null, values);
            }
        }

        //the button for wireless button
        wl_guest = (Button) findViewById(R.id.guest_wl);

        wl_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent welcome = new Intent(GuestStartGame.this, GuestWL.class);
                        welcome.putExtra("name", getName);
                        welcome.putExtra("start", start);
                        welcome.putExtra("volume", volume);
                        welcome.putExtra("pos", mysong.getCurrentPosition());
                        startActivity(welcome);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                    }
                }, 10);
            }
        });

        //the button for security button
        se_guest = (Button) findViewById(R.id.guest_sec);

        se_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent welcome = new Intent(GuestStartGame.this, GuestSEC.class);
                        welcome.putExtra("name", getName);
                        welcome.putExtra("start", start);
                        welcome.putExtra("volume", volume);
                        welcome.putExtra("pos", mysong.getCurrentPosition());
                        startActivity(welcome);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                    }
                }, 10);
            }
        });

        //the button for database button
        db_guest = (Button) findViewById(R.id.guest_db);

        db_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent welcome = new Intent(GuestStartGame.this, GuestDB.class);
                        welcome.putExtra("name", getName);
                        welcome.putExtra("start", start);
                        welcome.putExtra("volume", volume);
                        welcome.putExtra("pos", mysong.getCurrentPosition());
                        startActivity(welcome);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                    }
                }, 10);
            }
        });
    }

    //the movement when nav start or back
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.guest_start_game, menu);
        return true;
    }

    //this apps did not use this part. this position is up and right
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    //the menu in nav
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_edit) {
            displaySelectedScreen(R.id.nav_edit);
        } else if (id == R.id.nav_setting) {
            displaySelectedScreen(R.id.nav_setting);
        } else if (id == R.id.nav_back) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent welcome = new Intent(GuestStartGame.this, OpeningMenu.class);
                    welcome.putExtra("start", start);
                    welcome.putExtra("volume", volume);
                    welcome.putExtra("pos", mysong.getCurrentPosition());
                    startActivity(welcome);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }, 10);
        } else if (id == R.id.nav_reset) {
            SQLiteDatabase db = helper.getWritableDatabase();

            String sql = "DELETE FROM "+TABLE_GuestPass+" WHERE "+KEY_Gname+" = ? ";

            db.execSQL(sql, new String[]{getName});
        } /*else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //in navigation drawer activity must use dialogs Fragment
    private void displaySelectedScreen(int id){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (id){
            case R.id.nav_edit:
                RenameDialogsFreg dialogs = new RenameDialogsFreg();
                Bundle bundle = new Bundle();
                bundle.putString("oldname", getName);
                bundle.putString("start", start);
                bundle.putInt("volume", volume);
                bundle.putInt("pos", mysong.getCurrentPosition());
                dialogs.setArguments(bundle);
                mysong.stop();
                dialogs.show(fragmentTransaction, "MyCustomerDialogs");
                break;
            case R.id.nav_setting:
                SettingDialogsFreg dialogsFreg = new SettingDialogsFreg();
                Bundle bundle2 = new Bundle();
                bundle2.putString("oldname", getName);
                bundle2.putString("start", start);
                bundle2.putInt("volume", volume);
                bundle2.putInt("pos", mysong.getCurrentPosition());
                dialogsFreg.setArguments(bundle2);
                mysong.stop();
                dialogsFreg.show(fragmentTransaction, "MyCustomerDialogs");
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mysong.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mysong.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", getName);
        outState.putString("start", start);
        outState.putInt("volume", volume);
        outState.putInt("pos", mysong.getCurrentPosition());
    }
}


