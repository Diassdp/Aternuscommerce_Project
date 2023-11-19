package com.aplikasi.aternuscommerce;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private TextView debug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("userSession", MODE_PRIVATE);

        Button loginButton = findViewById(R.id.loginButton);
        Button profileButton = findViewById(R.id.profileButton);
        TextView debug = findViewById(R.id.debug);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        // Check if the user is logged in
        boolean isLoggedIn = sharedPreferences.contains("user_id");
        debug.setText("SharedPreferences Info:\n" +
                "User ID: " + sharedPreferences.getString("user_id", "Not logged in") +
                "\nIs Logged In: " + isLoggedIn);

        // Set visibility of the profile button based on the login status
        profileButton.setVisibility(isLoggedIn ? View.VISIBLE : View.GONE);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
            }
        });
    }
}
