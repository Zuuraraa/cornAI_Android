package com.example.moprog_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class RegisterActivity extends AppCompatActivity {
    private EditText etFullname, etPassword, etConfirmPassword;
    private String phoneNum;
    ImageButton btnBack;
    MaterialButton btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etFullname = findViewById(R.id.etFullname);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        phoneNum = getIntent().getStringExtra("PHONE_NUMBER");

        btnBack = findViewById(R.id.backBtn);
        btnBack.setOnClickListener(v -> {
            finish();
        });

        btnRegister = findViewById(R.id.verifyBtn);
        btnRegister.setOnClickListener(v ->{
            String fullname = etFullname.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            if (fullname.isEmpty()){
                etFullname.setError("Fullname is required!");
                etFullname.requestFocus();
                return;
            }

            if (password.isEmpty() || password.length() < 6) {
                etPassword.setError("Password must be at least 6 characters!");
                etPassword.requestFocus();
                return;
            }

            if (!password.equals(confirmPassword)) {
                etConfirmPassword.setError("Passwords do not match!");
                etConfirmPassword.requestFocus();
                return;
            }

            doRegister(fullname, phoneNum);
        });


    }

    private void doRegister(String fullname, String phoneNum) {
        //CODING FIREBASE /BACKEND
        Toast.makeText(this, "Welcome, " + fullname + "\nYour account has been created!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, HomePageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}