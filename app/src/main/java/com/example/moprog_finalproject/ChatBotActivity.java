package com.example.moprog_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ChatBotActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;

    private RecyclerView rvChat;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> chatMessageList;
    private EditText etMessage;
    private ImageButton sendBtn;
    private LinearLayout emptyState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat_bot);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvChat = findViewById(R.id.rvChat);
        etMessage = findViewById(R.id.etMessage);
        sendBtn = findViewById(R.id.sendBtn);
        emptyState = findViewById(R.id.emptyState);

        chatMessageList = new ArrayList<>();
        rvChat.setLayoutManager(new LinearLayoutManager(this));

        chatAdapter = new ChatAdapter(chatMessageList);
        rvChat.setAdapter(chatAdapter);

        sendBtn.setOnClickListener(v -> {
            String message = etMessage.getText().toString().trim();
            Toast.makeText(this, "Mengirim: " + message, Toast.LENGTH_SHORT).show();
            if (!message.isEmpty()) {
                sendMessage(message);
                etMessage.setText("");
            }
        });

        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.navHome) {
                Intent intent = new Intent(ChatBotActivity.this, HomePageActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                return true;
            } else if (id == R.id.navBot) {
                return true;
            } else if (id == R.id.navList) {
                Intent intent = new Intent(ChatBotActivity.this, HarvestActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                return true;
            } else if (id == R.id.navProfile) {
                Intent intent = new Intent(ChatBotActivity.this, ProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                return true;
            }
            return false;
        });
    }

    private void sendMessage(String message) {
        if (emptyState.getVisibility() == View.VISIBLE){
            emptyState.setVisibility(View.GONE);
        }

        chatMessageList.add(new ChatMessage(message, true));
        chatAdapter.notifyItemInserted(chatMessageList.size() - 1);
        rvChat.scrollToPosition(chatMessageList.size() - 1);

        new Handler().postDelayed(() -> {
            botResponse("Hi! I'm CornAI. Preparing your question about: " + message);
        }, 1500);
    }

    private void botResponse(String s) {
        chatMessageList.add(new ChatMessage(s, false));
        chatAdapter.notifyItemInserted(chatMessageList.size() - 1);
        rvChat.scrollToPosition(chatMessageList.size() - 1);
    }
}