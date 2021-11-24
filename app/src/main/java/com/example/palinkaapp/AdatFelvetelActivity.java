package com.example.palinkaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdatFelvetelActivity extends AppCompatActivity {
    private EditText editFozo;
    private EditText editGyumolcs;
    private EditText editAlkohol;
    private Button btnFelveszem;
    private Button btnVissza;
    private SQLiteDBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vissza = new Intent(AdatFelvetelActivity.this, MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });

        btnFelveszem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fozo = editFozo.getText().toString().trim();
                String gyumolcs = editGyumolcs.getText().toString().trim();
                String alkoholtartalom = editAlkohol.getText().toString().trim();
                if (fozo.isEmpty() || gyumolcs.isEmpty() || alkoholtartalom.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Minden mező megadása kötelező!", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        int alkoholTartalom = Integer.parseInt(alkoholtartalom);
                        if (alkoholTartalom < 0) {
                            Toast.makeText(getApplicationContext(), "Az alkoholTartalom" +
                                    "negatív szám nem lehet", Toast.LENGTH_SHORT).show();
                        }
                        else {
                             if (adatbazis.felvesz(fozo, gyumolcs, alkoholTartalom)) {
                                Toast.makeText(getApplicationContext(), "Sikeres felvétel",
                                        Toast.LENGTH_SHORT).show();
                            }
                             else {
                                 Toast.makeText(getApplicationContext(), "Sikertelen felvétel",
                                         Toast.LENGTH_SHORT).show();

                             }
                        }
                    }
                    catch (NumberFormatException e) {
                        Toast.makeText(getApplicationContext(), "Az alkohol tartalmának számnak kell lennie ",
                                Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });
    }

    private void init() {
        editFozo = findViewById(R.id.edit_fozo);
        editGyumolcs = findViewById(R.id.edit_gyumolcs);
        editAlkohol = findViewById(R.id.edit_alkoholtartalom);
        btnFelveszem = findViewById(R.id.btn_felvetel);
        btnVissza = findViewById(R.id.btn_vissza);
        adatbazis = new SQLiteDBHelper(this);
    }
}