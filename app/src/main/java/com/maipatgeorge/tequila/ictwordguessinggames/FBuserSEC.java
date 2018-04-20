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
import com.maipatgeorge.tequila.ictwordguessinggames.FBlev.FBSEC1;

import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_F_ID;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_ID;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_L_ID;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_catID;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_FbuserPass;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_Level;

public class FBuserSEC extends AppCompatActivity {

    Intent intent;
    Bundle bd;
    String getName;
    String fbid;

    MediaPlayer mysong;
    String start;
    int volume;
    int pos;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fbuser_sec);

        intent = getIntent();

        bd = intent.getExtras();

        if (savedInstanceState == null){
            start = intent.getStringExtra("start");
            volume = intent.getIntExtra("volume", 0);
            pos = intent.getIntExtra("pos", 0);
            if (bd != null) {
                getName = (String) bd.get("name");
                fbid = (String) bd.get("id");
            }
        } else {
            start = savedInstanceState.getString("start");
            volume = savedInstanceState.getInt("volume");
            pos = savedInstanceState.getInt("pos");
            getName = savedInstanceState.getString("name");
            fbid = savedInstanceState.getString("id");
        }

        float log1=(float)(Math.log(100-volume)/Math.log(volume));

        mysong = MediaPlayer.create(FBuserSEC.this, R.raw.feelingsohappy);
        mysong.start();
        mysong.setVolume(1-log1, 1-log1);
        mysong.setLooping(true);

        if (!start.equals("true")){
            mysong.stop();
        }

        getSupportActionBar().setTitle("ICT game");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new DBHelper(this);

        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "SELECT * FROM "+TABLE_FbuserPass+" WHERE "+KEY_L_ID+" = ( SELECT "+KEY_ID+" FROM "+TABLE_Level+" WHERE "+KEY_catID+" = 2) AND "+KEY_F_ID+" = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{fbid});

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

        correct1 = (ImageView) findViewById(R.id.fbselev12);
        correct2 = (ImageView) findViewById(R.id.fbselev22);
        correct3 = (ImageView) findViewById(R.id.fbselev32);
        correct4 = (ImageView) findViewById(R.id.fbselev42);
        correct5 = (ImageView) findViewById(R.id.fbselev52);
        correct6 = (ImageView) findViewById(R.id.fbselev62);
        correct7 = (ImageView) findViewById(R.id.fbselev72);
        correct8 = (ImageView) findViewById(R.id.fbselev82);
        correct9 = (ImageView) findViewById(R.id.fbselev92);

        ImageView[] corArr = new ImageView[]{correct1, correct2, correct3, correct4, correct5, correct6, correct7, correct8, correct9};

        if (id.length > 0){
            for (i = 0; i<id.length; i++){
                corArr[id[i]/4].setVisibility(View.VISIBLE);
            }
        }

        db.close();

        lv1 = (Button) findViewById(R.id.fbselev11);
        lv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent welcome = new Intent(FBuserSEC.this, FBSEC1.class);
                        welcome.putExtra("name", getName);
                        welcome.putExtra("id", fbid);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent welcome = new Intent(FBuserSEC.this, FBuserStartGame.class);
                    welcome.putExtra("name", getName);
                    welcome.putExtra("id", fbid);
                    welcome.putExtra("start", start);
                    welcome.putExtra("volume", volume);
                    welcome.putExtra("pos", mysong.getCurrentPosition());
                    startActivity(welcome);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }, 10);
        } /*
        else if (id == R.id.action_settings){

        }
        */

        return super.onOptionsItemSelected(item);
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
        outState.putString("id", fbid);
        outState.putString("start", start);
        outState.putInt("volume", volume);
        outState.putInt("pos", mysong.getCurrentPosition());
    }
}
