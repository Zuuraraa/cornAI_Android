package com.example.moprog_finalproject;

import android.content.Intent;
import android.os.Bundle;

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

public class HomePageActivity extends AppCompatActivity {
    RecyclerView rvArticle;
    ArticleAdapter adapter;
    List<Article> articleList;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homapage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvArticle = findViewById(R.id.rvArticle);
        rvArticle.setLayoutManager(new LinearLayoutManager(this));
        articleList = new ArrayList<>();

        articleList.add(new Article(
                "Cara Mencegah Hama Ulat Pada Tanaman Jagung",
                "Pelajari teknik pengendalian hama ulat grayak yang ramah lingkungan.",
                R.drawable.bg_article1
        ));

        articleList.add(new Article(
                "Harga Jagung Terkini 2025",
                "Update harga jagung pipil kering di tingkat petani Jawa Timur.",
                R.drawable.bg_article2
        ));

        articleList.add(new Article(
                "Tips Pemupukan Efektif",
                "Waktu terbaik memberikan pupuk NPK agar tongkol jagung besar.",
                R.drawable.bg_article3
        ));

        adapter = new ArticleAdapter(articleList);
        rvArticle.setAdapter(adapter);

        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.navHome) {
                return true;
            } else if (id == R.id.navBot) {
                Intent intent = new Intent(HomePageActivity.this, ChatBotActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                return true;
            } else if (id == R.id.navList) {
                Intent intent = new Intent(HomePageActivity.this, HarvestActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                return true;
            } else if (id == R.id.navProfile) {
                Intent intent = new Intent(HomePageActivity.this, ProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                return true;
            }
            return false;
        });
    }
}