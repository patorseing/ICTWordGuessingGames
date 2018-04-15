package com.maipatgeorge.tequila.ictwordguessinggames;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.maipatgeorge.tequila.ictwordguessinggames.Glev.GWL1;

public class GuestWL extends AppCompatActivity {

    Intent intent;
    Bundle bd;

    String getName;
    TextView textView;

    Button lv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_wl);

        textView = (TextView) findViewById(R.id.guest_wl);

        intent = getIntent();
        bd = intent.getExtras();

        if(bd != null)
        {
            getName = (String) bd.get("name");
        }

        getSupportActionBar().setTitle("ICT game");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv1 = (Button) findViewById(R.id.gwllev11);
        lv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent welcome = new Intent(GuestWL.this, GWL1.class);
                        welcome.putExtra("name", getName);
                        startActivity(welcome);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                    }
                }, 10);
            }
        });
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
                    Intent welcome = new Intent(GuestWL.this, GuestStartGame.class);
                    welcome.putExtra("name", getName);
                    startActivity(welcome);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }, 10);
        } else if (id == R.id.action_settings){

        }

        return super.onOptionsItemSelected(item);
    }
}
