package com.android2023.appseremi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnEduc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Boton Educacion.
        btnEduc = findViewById(R.id.btnEduca);
        btnEduc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pasar a la siguiente Activity.
                startActivity(new Intent(getApplicationContext(),Activity2.class));
                finish();
            }
        });
    }
}