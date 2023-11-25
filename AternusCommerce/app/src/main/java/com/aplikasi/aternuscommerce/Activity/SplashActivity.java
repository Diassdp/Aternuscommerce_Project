package com.aplikasi.aternuscommerce.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.aplikasi.aternuscommerce.R;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Make the activity full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        // Hide ActionBar if it exists
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // PostDelayed to launch MainActivity after a delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an intent to start MainActivity
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish(); // Finish the splash activity to prevent going back
            }
        }, SPLASH_DURATION);
    }
}

