package com.maipatgeorge.tequila.ictwordguessinggames;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
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
import com.squareup.picasso.Picasso;

import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_CAT;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_Category;
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
    String id;

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


        if(bd != null)
        {
            getName = (String) bd.get("name");
            id = (String) bd.get("id");
            name.setText(getName);

            //Picasso.with(this).load( "https://graph.facebook.com/"+id+"/picture?type=small").into((Target) profilePictureView);
            Picasso.with(this).load("https://graph.facebook.com/"+id+"/picture?type=large").into(profilePictureView);
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
                        welcome.putExtra("id", id);
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
                        welcome.putExtra("id", id);
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
                        welcome.putExtra("id", id);
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
            // Handle the camera action
        } else if (id == R.id.nav_out) {
            //LoginManager.getInstance().logOut();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent welcome = new Intent(FBuserStartGame.this, OpeningMenu.class);
                    startActivity(welcome);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }, 10);
        } /*else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
