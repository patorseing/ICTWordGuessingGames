package com.maipatgeorge.tequila.ictwordguessinggames;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    MediaPlayer mysong;
    String start;
    int volume;

    private static int Welcome_Timeout = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            volume = 100;
            start = "true";
        }

        float log1=(float)(Math.log(100-volume)/Math.log(volume));

        mysong = MediaPlayer.create(MainActivity.this, R.raw.feelingsohappy);
        mysong.start();
        mysong.setVolume(1-log1, 1-log1);
        mysong.setLooping(true);

        if (!start.equals("true")) {
            mysong.stop();
        }

                new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcome = new Intent(MainActivity.this, OpeningMenu.class);
                welcome.putExtra("start", start);
                welcome.putExtra("volume", volume);
                welcome.putExtra("pos", mysong.getCurrentPosition());
                startActivity(welcome);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, Welcome_Timeout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mysong.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mysong.stop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("start", start);
        outState.putInt("volume", volume);
        outState.putInt("pos", mysong.getCurrentPosition());
    }


}
