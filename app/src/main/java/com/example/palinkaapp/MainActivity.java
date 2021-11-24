package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

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
    }

    private void init() {
        btnListaz = findViewById(R.id.btn_listazasa);
        btnFelvesz = findViewById(R.id.btn_felvetel);
        btnKeres = findViewById(R.id.btn_keresese);
        adatbazis = new SQLiteDBHelper(this);
    }
}