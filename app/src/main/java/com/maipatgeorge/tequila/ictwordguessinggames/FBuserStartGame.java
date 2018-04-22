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
import com.maipatgeorge.tequila.ictwordguessinggames.freg.FBSettingDialogsFreg;
import com.squareup.picasso.Picasso;

import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_CAT;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_F_ID;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_Category;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_FbuserPass;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.cat_name;

public class FBuserStartGame extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    TextView name;
    ImageView profilePictureView;


    Intent intent;
    Bundle bd;

    DBHelper helper;

    Button wl_fbuser;
    Button se_fbuser;
    Button db_fbuser;

    String getName;
    String id2;

    MediaPlayer mysong;
    String start;
    int volume;
    int pos;
    float reduce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fbuser_start_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        profilePictureView = navigationView.getHeaderView(0).findViewById(R.id.imageViewFB);
        name = navigationView.getHeaderView(0).findViewById(R.id.fbname);

        helper = new DBHelper(this);


        intent = getIntent();

        bd = intent.getExtras();

        if (savedInstanceState == null){
            start = intent.getStringExtra("start");
            volume = intent.getIntExtra("volume", 0);
            pos = intent.getIntExtra("pos", 0);
            if(bd != null)
            {
                getName = (String) bd.get("name");
                id2 = (String) bd.get("id");
                name.setText(getName);

                //Picasso.with(this).load( "https://graph.facebook.com/"+id2+"/picture?type=small").into(profilePictureView);
                Picasso.with(this).load("https://graph.facebook.com/"+id2+"/picture?type=large").into(profilePictureView);
            }
        } else {
            start = savedInstanceState.getString("start");
            volume = savedInstanceState.getInt("volume");
            pos = savedInstanceState.getInt("pos");
            getName = savedInstanceState.getString("name");
            id2 = savedInstanceState.getString("id");
            name.setText(getName);
            Picasso.with(this).load("https://graph.facebook.com/"+id2+"/picture?type=large").into(profilePictureView);
        }

        reduce=(float)(100 - volume)/100;

        mysong = MediaPlayer.create(FBuserStartGame.this, R.raw.feelingsohappy);
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

        wl_fbuser = (Button) findViewById(R.id.fb_wl);

        wl_fbuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent welcome = new Intent(FBuserStartGame.this, FBuserWL.class);
                        welcome.putExtra("name", getName);
                        welcome.putExtra("id", id2);
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

        se_fbuser = (Button) findViewById(R.id.fb_sec);

        se_fbuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent welcome = new Intent(FBuserStartGame.this, FBuserSEC.class);
                        welcome.putExtra("name", getName);
                        welcome.putExtra("id", id2);
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

        db_fbuser = (Button) findViewById(R.id.fb_db);

        db_fbuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent welcome = new Intent(FBuserStartGame.this, FBuserDB.class);
                        welcome.putExtra("name", getName);
                        welcome.putExtra("id", id2);
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
        getMenuInflater().inflate(R.menu.fbuser_start_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
/*
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_settingFB) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            FBSettingDialogsFreg dialogsFreg = new FBSettingDialogsFreg();
            Bundle bundle2 = new Bundle();
            bundle2.putString("name", getName);
            bundle2.putString("id", id2);
            bundle2.putString("start", start);
            bundle2.putInt("volume", volume);
            bundle2.putInt("pos", mysong.getCurrentPosition());
            dialogsFreg.setArguments(bundle2);
            mysong.stop();
            dialogsFreg.show(fragmentTransaction, "MyCustomerDialogs");
        } else if (id == R.id.nav_out) {
            //LoginManager.getInstance().logOut();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent welcome = new Intent(FBuserStartGame.this, OpeningMenu.class);
                    welcome.putExtra("start", start);
                    welcome.putExtra("volume", volume);
                    welcome.putExtra("pos", mysong.getCurrentPosition());
                    startActivity(welcome);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }, 10);
        } else if (id == R.id.nav_resetFB) {
            SQLiteDatabase db = helper.getWritableDatabase();

            String sql = "DELETE FROM "+TABLE_FbuserPass+" WHERE "+KEY_F_ID+" = ?";

            db.execSQL(sql, new String[]{id2});
        } /*else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mysong.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mysong.stop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", getName);
        outState.putString("id", id2);
        outState.putString("start", start);
        outState.putInt("volume", volume);
        outState.putInt("pos", mysong.getCurrentPosition());
    }
}
