package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnListaz;
    private Button btnFelvesz;
    private Button btnKeres;
    private SQLiteDBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnFelvesz.setOnClickListener(v -> {
            Intent felvetelre = new Intent(MainActivity.this, AdatFelvetelActivity.class);
            startActivity(felvetelre);
            finish();
        });
    }

    private void init() {
        btnListaz = findViewById(R.id.btn_listazasa);
        btnFelvesz = findViewById(R.id.btn_felvetel);
        btnKeres = findViewById(R.id.btn_keresese);
        adatbazis = new SQLiteDBHelper(this);
    }
}