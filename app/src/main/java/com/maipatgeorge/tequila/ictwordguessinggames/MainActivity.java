package com.maipatgeorge.tequila.ictwordguessinggames;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private static int Welcome_Timeout = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ICT_gameDB").build();
        //AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcome = new Intent(MainActivity.this, OpeningMenu.class);
                startActivity(welcome);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, Welcome_Timeout);
    }
}
