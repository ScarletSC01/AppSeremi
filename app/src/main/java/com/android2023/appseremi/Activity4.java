package com.android2023.appseremi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Activity4 extends AppCompatActivity {

    TextView txtRutTeaOut, txtNombreOut, txtGrado;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        txtRutTeaOut = findViewById(R.id.txtRutTeaOut);
        txtNombreOut = findViewById(R.id.txtNombreOut);
        txtGrado = findViewById(R.id.txtGrado);

        txtGrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        Activity5.class);
                startActivity(intent);
                
            }
        });
        // Recibir los rut desde la activity n°2.
        String RutPaciente = getIntent().getStringExtra("RutPaciente");
        // Asignar el rut al campo de texto.
        txtRutTeaOut.setText(RutPaciente);

        // Metodo para consultar el nombre y grado de TEA
        ConsultarNombreyGradoTEA();
    }


    public void ConsultarNombreyGradoTEA() {



    }
}