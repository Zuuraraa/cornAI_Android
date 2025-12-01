package com.example.moprog_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class InputPhoneActivity extends AppCompatActivity {

    ImageButton backBtn;
    EditText etPhone;
    MaterialButton inputPhoneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input_phone);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        backBtn = findViewById(R.id.backBtn);
        etPhone = findViewById(R.id.etPhone);
        inputPhoneBtn = findViewById(R.id.inputPhoneBtn);

        backBtn.setOnClickListener(v -> {
            finish();
        });

        inputPhoneBtn.setOnClickListener(v -> {
            String phoneNum = etPhone.getText().toString();

            if (phoneNum.isEmpty()){
                etPhone.setError("Phone Number is Required!");
                etPhone.requestFocus();
                return;
            }

            if (phoneNum.length() < 11){
                etPhone.setError("Invalid Phone Number!");
                etPhone.requestFocus();
                return;
            }

            if (phoneNum.startsWith("0")){
                phoneNum = phoneNum.substring(1);
            }

            String fullPhoneNumber = "+62" + phoneNum;
            Intent intent = new Intent(InputPhoneActivity.this, OTPActivity.class);
            intent.putExtra("PHONE_NUMBER", fullPhoneNumber);
            startActivity(intent);

        });
    }
}