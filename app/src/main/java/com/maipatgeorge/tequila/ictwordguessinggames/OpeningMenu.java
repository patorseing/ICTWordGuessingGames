package com.maipatgeorge.tequila.ictwordguessinggames;

import android.content.Intent;
import android.os.Bundle;
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
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class OpeningMenu extends AppCompatActivity {

    LoginButton loginButton;
    TextView textView;
    CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    ProfileTracker profileTracker;
    Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //FacebookSdk.sdkInitialize(getApplicationContext());
        FacebookSdk.getApplicationContext();

        setContentView(R.layout.activity_opening_menu);

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
            public void onSuccess(LoginResult loginResult) {
                logOut.setVisibility(View.GONE);
                Profile profile = Profile.getCurrentProfile();
                nextActivity(profile);
                Toast.makeText(getApplicationContext(), "Loggin in ..." , Toast.LENGTH_SHORT).show();
                textView.setText("Login Success \n" + loginResult.getAccessToken().getUserId()+"\n"+loginResult.getAccessToken().getToken());
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
