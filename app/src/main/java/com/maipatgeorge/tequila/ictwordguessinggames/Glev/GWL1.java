package com.maipatgeorge.tequila.ictwordguessinggames.Glev;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maipatgeorge.tequila.ictwordguessinggames.DB.DBHelper;
import com.maipatgeorge.tequila.ictwordguessinggames.GuestWL;
import com.maipatgeorge.tequila.ictwordguessinggames.R;
import com.maipatgeorge.tequila.ictwordguessinggames.Screenshot;
import com.maipatgeorge.tequila.ictwordguessinggames.util.GifImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_Gname;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_ID;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_LEVEL;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_L_ID;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_catID;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_GuestPass;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_Level;

public class GWL1 extends AppCompatActivity {

    Intent intent;
    Bundle bd;

    String getName;

    DBHelper helper;

    String result;

    TextView s1;
    TextView s2;
    TextView s3;
    TextView s4;
    TextView s5;
    TextView s6;
    TextView s7;

    Button v1;
    Button v2;
    Button v3;
    Button v4;
    Button v5;
    Button v6;
    Button v7;
    Button v8;
    Button v9;

    Button delete;
    Button enter;

    ImageButton share;
    ImageView imageView;

    Dialog dialog;

    GifImageView congrats;
    Button back;
    Button stay;

    LinearLayout linearLayout;
    LinearLayout linearLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gwl1);

        final View rootView = getWindow().getDecorView().findViewById(android.R.id.content);

        //main = findViewById(R.id.GWLv1);

        helper = new DBHelper(this);

        intent = getIntent();
        bd = intent.getExtras();

        if(bd != null)
        {
            getName = (String) bd.get("name");
        }

        getSupportActionBar().setTitle("ICT game");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        result = "ANTENNA";

        String levsql = "SELECT  * FROM "+TABLE_Level+" WHERE "+KEY_LEVEL+" = ?";

        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery(levsql, new String[] {result});

        if (cursor.getCount() > 0){

        } else {
            ContentValues values = new ContentValues();
            values.put(KEY_ID, 1);
            values.put(KEY_LEVEL, result);
            values.put(KEY_catID, 1);
            db.insertOrThrow(TABLE_Level, null, values);
        }

        s1 = (TextView) findViewById(R.id.gwl1_Space1);
        s2 = (TextView) findViewById(R.id.gwl1_Space2);
        s3 = (TextView) findViewById(R.id.gwl1_Space3);
        s4 = (TextView) findViewById(R.id.gwl1_Space4);
        s5 = (TextView) findViewById(R.id.gwl1_Space5);
        s6 = (TextView) findViewById(R.id.gwl1_Space6);
        s7 = (TextView) findViewById(R.id.gwl1_Space7);

        final TextView[] Space = new TextView[]{s1, s2, s3, s4, s5, s6, s7};

        String sql = "SELECT  * FROM "+TABLE_GuestPass+" WHERE "+KEY_Gname+" = ? and " + KEY_L_ID +" = 1";
        SQLiteDatabase db1 = helper.getReadableDatabase();

        Cursor cursor1 = db1.rawQuery(sql, new String[]{getName});

        if (cursor1.getCount() > 0)
        {
            for (int i = 0; i<Space.length; i++){
                Space[i].setText(String.valueOf(result.charAt(i)));
            }
        }

        v1 = (Button) findViewById(R.id.gwl1_char1);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i< Space.length; i++){
                    if (Space[i].getText().equals("____")){
                        Space[i].setText(v1.getText());
                        v1.setText("____");
                        break;
                    }
                }
            }
        });

        v2 = (Button) findViewById(R.id.gwl1_char2);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i< Space.length; i++){
                    if (Space[i].getText().equals("____")){
                        Space[i].setText(v2.getText());
                        v2.setText("____");
                        break;
                    }
                }
            }
        });

        v3 = (Button) findViewById(R.id.gwl1_char3);
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i< Space.length; i++){
                    if (Space[i].getText().equals("____")){
                        Space[i].setText(v3.getText());
                        v3.setText("____");
                        break;
                    }
                }
            }
        });

        v4 = (Button) findViewById(R.id.gwl1_char4);
        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i< Space.length; i++){
                    if (Space[i].getText().equals("____")){
                        Space[i].setText(v4.getText());
                        v4.setText("____");
                        break;
                    }
                }
            }
        });

        v5 = (Button) findViewById(R.id.gwl1_char5);
        v5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i< Space.length; i++){
                    if (Space[i].getText().equals("____")){
                        Space[i].setText(v5.getText());
                        v5.setText("____");
                        break;
                    }
                }
            }
        });

        v6 = (Button) findViewById(R.id.gwl1_char6);
        v6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i< Space.length; i++){
                    if (Space[i].getText().equals("____")){
                        Space[i].setText(v6.getText());
                        v6.setText("____");
                        break;
                    }
                }
            }
        });

        v7 = (Button) findViewById(R.id.gwl1_char7);
        v7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i< Space.length; i++){
                    if (Space[i].getText().equals("____")){
                        Space[i].setText(v7.getText());
                        v7.setText("____");
                        break;
                    }
                }
            }
        });

        v8 = (Button) findViewById(R.id.gwl1_char8);
        v8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i< Space.length; i++){
                    if (Space[i].getText().equals("____")){
                        Space[i].setText(v8.getText());
                        v8.setText("____");
                        break;
                    }
                }
            }
        });

        v9 = (Button) findViewById(R.id.gwl1_char9);
        v9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < Space.length; i++){
                    if (Space[i].getText().equals("____")){
                        Space[i].setText(v9.getText());
                        v9.setText("____");
                        break;
                    }
                }
            }
        });

        final Button[] type = new Button[]{v1, v2, v3, v4, v5, v6, v7, v8, v9};

        delete = (Button) findViewById(R.id.gwl1_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = Space.length-1; i >= 0; i--){
                    if (!Space[i].getText().equals("____")){
                        for (int j = 0; j<type.length; j++){
                            if (type[j].getText().equals("____")){
                                type[j].setText(Space[i].getText());
                                break;
                            }
                        }
                        Space[i].setText("____");
                        break;
                    }
                }
            }
        });

        enter = (Button) findViewById(R.id.gwl1_enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = 0;
                for (int i = 0; i<Space.length;i++){
                    if (!Space[i].getText().equals(String.valueOf(result.charAt(i)))){
                        break;
                    }
                    count++;
                }
                if (count == Space.length){
                    String sql = "SELECT  * FROM "+TABLE_GuestPass+" WHERE "+KEY_Gname+" = ? and " + KEY_L_ID +" = 1";
                    SQLiteDatabase db1 = helper.getReadableDatabase();

                    Cursor cursor1 = db1.rawQuery(sql, new String[]{getName});

                    String id[] = new String[cursor1.getCount()];
                    if (cursor1.getCount() > 0) {

                    } else {
                        SQLiteDatabase db = helper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put(KEY_Gname, getName);
                        values.put(KEY_L_ID, 1);
                        db.insertOrThrow(TABLE_GuestPass, null, values);
                        db.close();
                        db1.close();
                    }

                    myDialogs();

                } else {
                    for (int i = 0; i < Space.length; i++){
                        for (int j = 0; j < type.length; j++){
                            if (type[j].getText().equals("____")){
                                type[j].setText(Space[i].getText());
                                break;
                            }
                        }
                        Space[i].setText("____");
                    }
                    Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageView = (ImageView) findViewById(R.id.GWL1);

        share = (ImageButton) findViewById(R.id.shareWL1);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap b = Screenshot.takescreenshotOfRootview(imageView);
                startShare(b);
                //imageView.setImageBitmap(b);
                //main.setBackgroundColor(Color.parseColor("#999999 "));
            }
        });
    }

    private void startShare(Bitmap b) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        File file = new File(Environment.getExternalStorageDirectory()+File.separator+"shot.jpg");
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/shot.jpg"));
        startActivity(Intent.createChooser(intent, "share image"));
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
                    Intent welcome = new Intent(GWL1.this, GuestWL.class);
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

    public void myDialogs(){
        dialog = new Dialog(GWL1.this);
        dialog.setContentView(R.layout.dialogscongres);

        linearLayout = new LinearLayout(GWL1.this);
        linearLayout = (LinearLayout) dialog.findViewById(R.id.l1);
        linearLayout.setEnabled(true);

        congrats = new GifImageView(GWL1.this);
        congrats = (GifImageView) dialog.findViewById(R.id.congratss);
        congrats.setGifImageResource(R.drawable.congratulation);
        congrats.setEnabled(true);

        linearLayout = new LinearLayout(GWL1.this);
        linearLayout2 = (LinearLayout) dialog.findViewById(R.id.l2);
        linearLayout.setEnabled(true);

        back = (Button) dialog.findViewById(R.id.conback);
        back.setEnabled(true);
        stay = (Button) dialog.findViewById(R.id.constay);
        stay.setEnabled(true);
        dialog.show();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent welcome = new Intent(GWL1.this, GuestWL.class);
                        welcome.putExtra("name", getName);
                        startActivity(welcome);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                    }
                }, 10);
            }
        });

        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }
}
