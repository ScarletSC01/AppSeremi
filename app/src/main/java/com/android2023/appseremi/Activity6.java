package com.android2023.appseremi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class Activity6 extends AppCompatActivity {

    Button btnActividad , btnCrisis;
    TextView txtOClinica, txtFamiliar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);

        btnActividad = findViewById(R.id.btnActividad);
        btnCrisis = findViewById(R.id.btnCrisis);
        txtOClinica = findViewById(R.id.btnActividad);
        txtFamiliar = findViewById(R.id.txtOFamiliar);

        btnActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        Activity7.class);
                startActivity(intent);

           btnCrisis.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(
                           getApplicationContext(),
                           Activity8.class);
                   startActivity(intent);
               }
           });
            }
        });
    }
}