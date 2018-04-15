package com.maipatgeorge.tequila.ictwordguessinggames;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.maipatgeorge.tequila.ictwordguessinggames.DB.DBHelper;

import org.json.JSONObject;

import java.util.UUID;

import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_FBID;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_ID;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_NAME;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_TOKEN;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_Fbuser;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_Guest;

public class OpeningMenu extends AppCompatActivity {

    LoginButton loginButton;
    TextView textView;
    CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    ProfileTracker profileTracker;
    Button logOut;
    Button asGuest;
    Button asOldGuest;

    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //FacebookSdk.sdkInitialize(getApplicationContext());
        FacebookSdk.getApplicationContext();

        setContentView(R.layout.activity_opening_menu);

        helper = new DBHelper(this);

        loginButton = (LoginButton)findViewById(R.id.fd_login_bn);
        textView = (TextView)findViewById(R.id.result);
        logOut = (Button) findViewById(R.id.out);
        callbackManager = CallbackManager.Factory.create();

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                nextActivity(currentProfile);
            }
        };

        accessTokenTracker.startTracking();
        profileTracker.startTracking();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                logOut.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Loggin in ..." , Toast.LENGTH_SHORT).show();

                //textView.setText("Login Success \n" +userId+"\n"+token);
                //textView.setText(profile.getId());

                GraphRequest.newMeRequest(
                        loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(final JSONObject me, GraphResponse response) {
                                if (response.getError() != null) {
                                    // handle error
                                } else {
                                    String token = loginResult.getAccessToken().getToken();
                                    final String fbUserId = me.optString("id");
                                    final String fbUserName = me.optString("name");

                                    String fbcount = "SELECT * FROM "+TABLE_Fbuser+" WHERE "+KEY_FBID+" = ? ";

                                    SQLiteDatabase db1 = helper.getReadableDatabase();

                                    Cursor cursor = db1.rawQuery(fbcount, new String[] {fbUserId});

                                    if (cursor.getCount() > 0){
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent welcome = new Intent(OpeningMenu.this, FBuserStartGame.class);
                                                welcome.putExtra("name", fbUserName);
                                                welcome.putExtra("id", fbUserId);
                                                startActivity(welcome);
                                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                                finish();
                                            }
                                        }, 10);
                                    }
                                    else {
                                        SQLiteDatabase db2 = helper.getWritableDatabase();
                                        ContentValues values = new ContentValues();
                                        values.put(KEY_FBID, fbUserId);
                                        values.put(KEY_NAME, fbUserName);
                                        values.put(KEY_TOKEN, token);
                                        db2.insertOrThrow(TABLE_Fbuser, null, values);
                                        db2.close();

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent welcome = new Intent(OpeningMenu.this, FBuserStartGame.class);
                                                welcome.putExtra("name", fbUserName);
                                                welcome.putExtra("id", fbUserId);
                                                startActivity(welcome);
                                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                                finish();
                                            }
                                        }, 10);
                                   }
                                }
                            }
                        }).executeAsync();

                //visibility
                loginButton.setVisibility(View.GONE);
                logOut.setVisibility(View.VISIBLE);
                logOut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LoginManager.getInstance().logOut();
                        textView.setText("Logout");
                        logOut.setVisibility(View.GONE);
                        loginButton.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void onCancel() {
                textView.setText("Login cancel");
            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        asGuest = findViewById(R.id.guest);

        asGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final UUID uuid = UUID.randomUUID();
                final String name = uuid.toString().replace("-", "").substring(0, 15);
                textView.setText(name);
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(KEY_NAME, name);
                db.insertOrThrow(TABLE_Guest, null, values);
                db.close();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent welcome = new Intent(OpeningMenu.this, GuestStartGame.class);
                        welcome.putExtra("name", name);
                        startActivity(welcome);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                    }
                }, 10);

            }
        });

        asOldGuest = findViewById(R.id.asoldguest);

        asOldGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guestLastQuery = "SELECT  * FROM "+TABLE_Guest+" WHERE "+KEY_ID+" = (SELECT max("+KEY_ID+") FROM "+TABLE_Guest+")";
                SQLiteDatabase db = helper.getReadableDatabase();

                Cursor cursor = db.rawQuery(guestLastQuery, null);

                String id[] = new String[cursor.getCount()];
                int i = 0;
                if (cursor.getCount() > 0)
                {
                    cursor.moveToFirst();
                    do {
                        id[i] = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                        i++;
                    } while (cursor.moveToNext());
                    cursor.close();

                    final String name = id[0];

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent welcome = new Intent(OpeningMenu.this, GuestStartGame.class);
                            welcome.putExtra("name", name);
                            startActivity(welcome);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            finish();
                        }
                    }, 10);
                } else {
                    Toast.makeText(getApplicationContext(),"You don't have old guest user",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onResume(){
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        nextActivity(profile);
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    protected void onStop(){
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    protected void nextActivity(Profile profile){
        if (profile != null){
            LoginManager.getInstance().logOut();
        }
    }
}