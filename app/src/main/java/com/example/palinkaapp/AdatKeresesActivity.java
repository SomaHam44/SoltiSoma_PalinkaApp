package com.example.palinkaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdatKeresesActivity extends AppCompatActivity {
    private EditText editFozo;
    private EditText editGyumolcs;
    private Button btnKereso;
    private Button btnVisszater;
    private TextView textKeresesnel;
    private SQLiteDBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnVisszater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent visszatero = new Intent(AdatKeresesActivity.this, MainActivity.class);
                startActivity(visszatero);
                finish();
            }
        });

        btnKereso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fozos = editFozo.getText().toString().trim();
                String gyumolcse = editGyumolcs.getText().toString().trim();
                if (fozos.isEmpty() || gyumolcse.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Minden mező kitöltése kötelező",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        editFozo = findViewById(R.id.edit_fozoje);
        editGyumolcs = findViewById(R.id.edit_gyumolcse);
        btnKereso = findViewById(R.id.btn_keres);
        btnVisszater = findViewById(R.id.btn_visszater);
        textKeresesnel = findViewById(R.id.text_keresesnel);
        adatbazis = new SQLiteDBHelper(this);
    }
}