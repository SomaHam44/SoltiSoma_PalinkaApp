package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnListaz;
    private Button btnFelvesz;
    private Button btnKeres;
    private SQLiteDBHelper adatbazis;
    private TextView textListank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        textListank.setMovementMethod(new ScrollingMovementMethod());

        btnFelvesz.setOnClickListener(v -> {
            Intent felvetelre = new Intent(MainActivity.this, AdatFelvetelActivity.class);
            startActivity(felvetelre);
            finish();
        });

        btnKeres.setOnClickListener(v -> {
            Intent keresesre = new Intent(MainActivity.this, AdatKeresesActivity.class);
            startActivity(keresesre);
            finish();
        });

        btnListaz.setOnClickListener(v -> {
            Cursor adataink = adatbazis.listaz();
            if (adataink.getCount() == 0) {
                Toast.makeText(this, "Nincs felvéve adat az adatbázisban", Toast.LENGTH_SHORT).show();

            }
            else {
                StringBuilder builder = new StringBuilder();
                while (adataink.moveToNext()) {
                    builder.append("ID: ").append(adataink.getInt(0));
                    builder.append(System.lineSeparator());
                    builder.append("Főző: ").append(adataink.getString(1));
                    builder.append(System.lineSeparator());
                    builder.append("Gyümölcs: ").append(adataink.getString(2));
                    builder.append(System.lineSeparator());
                    builder.append("Alkohol: ").append(adataink.getInt(3));
                    builder.append(System.lineSeparator());
                    builder.append(System.lineSeparator());

                }
                textListank.setText(builder.toString());
            }
        });
    }

    private void init() {
        btnListaz = findViewById(R.id.btn_listazasa);
        btnFelvesz = findViewById(R.id.btn_felvetel);
        btnKeres = findViewById(R.id.btn_keresese);
        adatbazis = new SQLiteDBHelper(this);
        textListank = findViewById(R.id.text_lista);
    }
}