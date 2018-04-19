package com.maipatgeorge.tequila.ictwordguessinggames;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import com.maipatgeorge.tequila.ictwordguessinggames.DB.DBHelper;

public class GuestSEC extends AppCompatActivity {

    Intent intent;
    Bundle bd;

    String getName;

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
        setContentView(R.layout.activity_guest_sec);

        intent = getIntent();

        start = intent.getStringExtra("start");
        volume = intent.getIntExtra("volume", 0);
        pos = intent.getIntExtra("pos", 0);

        float log1=(float)(Math.log(100-volume)/Math.log(volume));

        mysong = MediaPlayer.create(GuestSEC.this, R.raw.feelingsohappy);
        mysong.seekTo(pos);
        mysong.start();
        mysong.setVolume(1 - log1, 1 - log1);
        mysong.setLooping(true);

        if (!start.equals("true")){
            mysong.stop();
        }

        getSupportActionBar().setTitle("ICT game");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bd = intent.getExtras();

        if(bd != null)
        {
            getName = (String) bd.get("name");
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent welcome = new Intent(GuestSEC.this, GuestStartGame.class);
                    intent = getIntent();
                    bd = intent.getExtras();

                    if(bd != null)
                    {
                        getName = (String) bd.get("name");
                    }
                    welcome.putExtra("name", getName);
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
    protected void onPause(){
        super.onPause();
        mysong.stop();
    }

    protected void onStop(){
        super.onStop();
        mysong.stop();
    }
}
