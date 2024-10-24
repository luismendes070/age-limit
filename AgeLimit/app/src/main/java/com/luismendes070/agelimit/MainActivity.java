package com.luismendes070.agelimit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
// import com.google.android.gms.games.Games;


import java.util.Calendar;// ChatGPT

// import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

// import androidx.appcompat.app.AppCompatActivity;

// import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.luismendes070.agelimit.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
// import android.widget.Button;
// import android.widget.EditText;
// import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient googleSignInClient;

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });

        // ChatGPT
        Button submitButton = findViewById(R.id.submit_btn);
        EditText dobInput = findViewById(R.id.dob_input);

        submitButton.setOnClickListener(view -> {
            String dob = dobInput.getText().toString();
            if (!dob.isEmpty()) {
                int age = calculateAge(dob);
                if (age < 18) {
                    // Show an alert or block access
                    Toast.makeText(MainActivity.this, "You must be 18 or older to use this feature.", Toast.LENGTH_SHORT).show();
                } else {
                    // Proceed with the feature
                    Toast.makeText(MainActivity.this, "Access granted!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // ChatGPT
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        Button signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(view -> signIn());

    } // end onCreate method

    // Method to calculate age based on date of birth
    private int calculateAge(String dob) {
        String[] parts = dob.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        Calendar dobCalendar = Calendar.getInstance();
        dobCalendar.set(year, month - 1, day);

        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dobCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return age;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    // ChatGPT
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
            if (account != null) {
                verifyAgeAndProceed(account);
            } else {
                Toast.makeText(this, "Sign-in failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void verifyAgeAndProceed(GoogleSignInAccount account) {
        // Simulate fetching the date of birth
        String dob = "2000-01-01"; // Replace this with actual DOB retrieval
        int age = calculateAge(dob);

        if (age < 18) {
            Toast.makeText(this, "You must be 18 or older to use this feature.", Toast.LENGTH_SHORT).show();
        } else {
            // Proceed with accessing Play Games Services
            Toast.makeText(this, "Access granted!", Toast.LENGTH_SHORT).show();
            // Load your game features or services
        }
    }



}