package com.example.moprog_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    MaterialButton phoneBtn;
    MaterialButton googleBtn;
    MaterialButton appleBtn;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        phoneBtn = findViewById(R.id.phoneBtn);
        googleBtn = findViewById(R.id.googleBtn);
        appleBtn = findViewById(R.id.appleBtn);
        tvLogin = findViewById(R.id.tvLogin);

        phoneBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InputPhoneActivity.class);
            startActivity(intent);
        });

        googleBtn.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Google Firebase Here!", Toast.LENGTH_SHORT).show();
        });

        appleBtn.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
        });

        tvLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}