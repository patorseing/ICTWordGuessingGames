package com.maipatgeorge.tequila.ictwordguessinggames;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.maipatgeorge.tequila.ictwordguessinggames.DB.DBHelper;
import com.maipatgeorge.tequila.ictwordguessinggames.Glev.GWL1;

import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_Gname;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_ID;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_L_ID;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_catID;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_GuestPass;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_Level;

public class GuestWL extends AppCompatActivity {

    Intent intent;
    Bundle bd;

    String getName;


    DBHelper helper;

    ImageView correct1;
    ImageView correct2;
    ImageView correct3;
    ImageView correct4;
    ImageView correct5;
    ImageView correct6;
    ImageView correct7;
    ImageView correct8;
    ImageView correct9;

    Button lv1;

    MediaPlayer mysong;
    String start;
    int volume;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_wl);

        intent = getIntent();

        start = intent.getStringExtra("start");
        volume = intent.getIntExtra("volume", 0);
        pos = intent.getIntExtra("pos", 0);

        float log1=(float)(Math.log(100-volume)/Math.log(volume));

        mysong = MediaPlayer.create(GuestWL.this, R.raw.feelingsohappy);
        mysong.seekTo(pos);
        mysong.start();
        mysong.setVolume(1 - log1, 1 - log1);
        mysong.setLooping(true);

        if (!start.equals("true")){
            mysong.stop();
        }

        intent = getIntent();
        bd = intent.getExtras();

        if(bd != null)
        {
            getName = (String) bd.get("name");
        }

        getSupportActionBar().setTitle("ICT game");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new DBHelper(this);

        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "SELECT * FROM "+TABLE_GuestPass+" WHERE "+KEY_L_ID+" = ( SELECT "+KEY_ID+" FROM "+TABLE_Level+" WHERE "+KEY_catID+" = 1) AND "+KEY_Gname+" = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{getName});

        int id[] = new int[cursor.getCount()];
        int i = 0;

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                id[i] = cursor.getInt(cursor.getColumnIndex(KEY_L_ID));
                i++;
            } while (cursor.moveToNext());
            cursor.close();
        }

        correct1 = (ImageView) findViewById(R.id.gwllev12);
        correct2 = (ImageView) findViewById(R.id.gwllev22);
        correct3 = (ImageView) findViewById(R.id.gwllev32);
        correct4 = (ImageView) findViewById(R.id.gwllev42);
        correct5 = (ImageView) findViewById(R.id.gwllev52);
        correct6 = (ImageView) findViewById(R.id.gwllev62);
        correct7 = (ImageView) findViewById(R.id.gwllev72);
        correct8 = (ImageView) findViewById(R.id.gwllev82);
        correct9 = (ImageView) findViewById(R.id.gwllev92);

        ImageView[] corArr = new ImageView[]{correct1, correct2, correct3, correct4, correct5, correct6, correct7, correct8, correct9};

        if (id.length > 0){
            for (i = 0; i<id.length; i++){
                corArr[id[i]/3].setVisibility(View.VISIBLE);
            }
        }

        db.close();

        lv1 = (Button) findViewById(R.id.gwllev11);
        lv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent welcome = new Intent(GuestWL.this, GWL1.class);
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
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent welcome = new Intent(GuestWL.this, GuestStartGame.class);
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
        /*
        else if (id == R.id.action_settings){


        }
        */

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause(){
        super.onPause();
        mysong.stop();
    }

    protected void onStop(){
        super.onStop();
        mysong.stop();
    }
}
