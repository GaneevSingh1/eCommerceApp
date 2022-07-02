package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        fragmentManager = getSupportFragmentManager();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);

        BiometricManager biometricManager = BiometricManager.from(this);

        switch(biometricManager.canAuthenticate(BiometricManager.Authenticators.DEVICE_CREDENTIAL))
        {
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(getApplicationContext(), "No Fingerprint Hardware Found", Toast.LENGTH_LONG).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(getApplicationContext(), "Fingerprint Not Working", Toast.LENGTH_LONG).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(getApplicationContext(), "No Fingerprint Assigned", Toast.LENGTH_LONG).show();
                break;
        }

        Executor executor = ContextCompat.getMainExecutor(this);

        biometricPrompt = new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {

            private int login_attempt = 0;
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(), "Fingerprint Unavailable", Toast.LENGTH_LONG).show();
                MainActivity.this.finish();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(), "Fingerprint Successful", Toast.LENGTH_LONG).show();
                mDrawerLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                login_attempt++;
                if(login_attempt >= 3) {
                    Toast.makeText(getApplicationContext(), "Fingerprint Failed", Toast.LENGTH_LONG).show();
                    MainActivity.this.finish();
                }
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("eCommerce App")
                .setDescription("Use Fingerprint To Login")
                .setNegativeButtonText("Use account password")
                .build();

        biometricPrompt.authenticate(promptInfo);

        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, SummaryFragment.class, null)
                .commit();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        Intent ItemsIntent;
        switch (item.getItemId()) {
            case R.id.menu_option_1:
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, SummaryFragment.class, null)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.menu_option_2:
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, SalesFragment.class, null)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.menu_option_3:
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, ItemsFragment.class, null)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.menu_option_4:
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, CustomersFragment.class, null)
                        .addToBackStack(null)
                        .commit();
                break;
        }
        //close navigation drawer
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}