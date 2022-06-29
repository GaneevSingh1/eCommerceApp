package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    private ConstraintLayout mMainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainLayout = findViewById(R.id.main_layout);

        BiometricManager biometricManager = BiometricManager.from(this);

        switch(biometricManager.canAuthenticate(BiometricManager.Authenticators.DEVICE_CREDENTIAL))
        {
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(getApplicationContext(), "No Fingerprint Hardware Found", Toast.LENGTH_LONG).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(getApplicationContext(), "Fingerprint Not Working", Toast.LENGTH_LONG).show();
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(getApplicationContext(), "No Fingerprint Assigned", Toast.LENGTH_LONG).show();
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
                mMainLayout.setVisibility(View.VISIBLE);
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
    }
}