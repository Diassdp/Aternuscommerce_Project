package com.aplikasi.aternuscommerce.Activity.Feature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aplikasi.aternuscommerce.API.Connection;
import com.aplikasi.aternuscommerce.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {

    private EditText fullNameEditText, emailEditText, passwordEditText;
    private SharedPreferences sharedPreferences;
    private Button saveButton, logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Profile");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        sharedPreferences = getSharedPreferences("userSession", MODE_PRIVATE);

        fullNameEditText = findViewById(R.id.fullNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        saveButton = findViewById(R.id.saveButton);
        logoutButton = findViewById(R.id.logoutButton);
        populateUserData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }


    private void populateUserData() {
        //PopulateUserData function used to call the user data from database
        Connection connection = new Connection();
        String url = connection.getUrlSetup() + "populate.php";
        RequestQueue queue = Volley.newRequestQueue(this);

        String userId = sharedPreferences.getString("user_id", "");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        String fullName = jsonResponse.getString("fullname");
                        String email = jsonResponse.getString("email");
                        fullNameEditText.setText(fullName);
                        emailEditText.setText(email);
                    } else {
                        String message = jsonResponse.getString("message");
                        Toast.makeText(Profile.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Profile.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Sending Parameter Or Data into PHP API
                Map<String, String> params = new HashMap<>();
                params.put("user_id", userId); // Pass the user_id to the PHP script
                return params;
            }

        };
        queue.add(stringRequest);
    }

    private void saveChanges() {
        // saveChanges Function to update the userdata
        Connection connection = new Connection();
        String url = connection.getUrlSetup() + "Update.php";
        String fullname = fullNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Retrieve user_id from SharedPreferences
        String userId = sharedPreferences.getString("user_id", "");
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    String message = jsonResponse.getString("message");
                    if (success) {
                        // Handle successful update, e.g., show a success message
                        Toast.makeText(Profile.this, message, Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle update failure, e.g., show an error message
                        Toast.makeText(Profile.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Profile.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Sending Parameters or Data into PHP API
                Map<String, String> params = new HashMap<>();
                params.put("user_id", userId);
                params.put("fullname", fullname);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        queue.add(stringRequest);
    }
    private void logout() {//Logout Function
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("user_id");
        editor.apply();

        Intent intent = new Intent(Profile.this, Login.class);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
