package com.example.vinayakks.stolxnew;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseUser;


public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "f3cTZllchvdk0KzDKgmQlkHMARr0Q2yQsphmfHub", "x5xIvZFjdzBxXqYVvXy5Hl9mF4EBUJ222A1M3Z0m");

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                ParseUser user = ParseUser.getCurrentUser();
                if(user!=null){
                    Intent i = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }

                // close this activity

            }
        }, SPLASH_TIME_OUT);
    }

    public void onDestroy()
    {
        super.onDestroy();

    }
}

