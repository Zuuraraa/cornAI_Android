package com.example.moprog_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class OTPActivity extends AppCompatActivity {
    private EditText otp1, otp2, otp3, otp4;
    private TextView tvResendTimer, tvDescOTP;
    private MaterialButton verifyBtn;
    ImageButton backBtn;
    private String receivedPhoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otpactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        otp1 = findViewById(R.id.OTP1);
        otp2 = findViewById(R.id.OTP2);
        otp3 = findViewById(R.id.OTP3);
        otp4 = findViewById(R.id.OTP4);
        tvResendTimer = findViewById(R.id.tvResendTimer);
        tvDescOTP = findViewById(R.id.tvDescOTP);
        verifyBtn = findViewById(R.id.verifyBtn);
        
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> {
            finish();
        });
        
        receivedPhoneNum = getIntent().getStringExtra("PHONE_NUMBER");
        if (receivedPhoneNum != null){
            tvDescOTP.setText("We have sent the code verification to\n" + receivedPhoneNum);
        }
        
        setupOTPInput();
        
        startTimer(50000);

        verifyBtn.setOnClickListener(v -> {
            String code = getOTPCode();

            if (code.isEmpty()) {
                Toast.makeText(OTPActivity.this, "OTP Code is Required!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (code.length() < 4) {
                Toast.makeText(OTPActivity.this, "Invalid OTP Code!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (code.equals("1234")) {
                Toast.makeText(OTPActivity.this, "OTP Code is Correct!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(OTPActivity.this, RegisterActivity.class);
                intent.putExtra("PHONE_NUMBER", receivedPhoneNum);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(OTPActivity.this, "OTP Code is Incorrect!", Toast.LENGTH_SHORT).show();
                otp1.setText("");
                otp2.setText("");
                otp3.setText("");
                otp4.setText("");
                otp1.requestFocus();
            }
        });
    }

    private String getOTPCode() {
        return otp1.getText().toString() + otp2.getText().toString() + otp3.getText().toString() + otp4.getText().toString();
    }

    private void startTimer(long duration) {
        new CountDownTimer(duration, 1000) {
            @Override
            public void onFinish() {
                tvResendTimer.setText("Resend Code");
                tvResendTimer.setTextColor(getResources().getColor(R.color.black));
            }

            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                tvResendTimer.setText("Resend code in 00:" + seconds);
            }
        }.start();
    }

    private void setupOTPInput() {
        otp1.addTextChangedListener(new TextWatcher(){
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    otp2.requestFocus();
                }
            }
            public void afterTextChanged(Editable s) {}
        });

        otp2.addTextChangedListener(new TextWatcher(){
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    otp3.requestFocus();
                }
            }
            public void afterTextChanged(Editable s) {}
        });

        otp3.addTextChangedListener(new TextWatcher(){
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    otp4.requestFocus();
                }
            }
            public void afterTextChanged(Editable s) {}
        });
    }
}