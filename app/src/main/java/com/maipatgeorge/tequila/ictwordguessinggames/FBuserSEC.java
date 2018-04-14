package com.maipatgeorge.tequila.ictwordguessinggames;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class FBuserSEC extends AppCompatActivity {

    Intent intent;
    Bundle bd;
    String getName;
    String fbid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fbuser_sec);

        getSupportActionBar().setTitle("ICT game");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent welcome = new Intent(FBuserSEC.this, FBuserStartGame.class);
                    intent = getIntent();
                    bd = intent.getExtras();
                    if(bd != null)
                    {
                        getName = (String) bd.get("name");
                        fbid = (String) bd.get("id");
                        welcome.putExtra("name", getName);
                        welcome.putExtra("id", fbid);
                        startActivity(welcome);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                    }
                }
            }, 10);
        } else if (id == R.id.action_settings){

        }

        return super.onOptionsItemSelected(item);
    }
}
