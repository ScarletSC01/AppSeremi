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
import com.google.firebase.database.FirebaseDatabase;
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
        String RutPaciente = getIntent().getStringExtra("RutPaciente");
        txtGrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        Activity5.class);
                intent.putExtra("RutPaciente", RutPaciente);
                startActivity(intent);

            }
        });

        txtRutTeaOut.setText(RutPaciente);

        // Metodo para consultar el nombre y grado de TEA
        ConsultarNombreyGradoTEA();
    }


    public void ConsultarNombreyGradoTEA() {
        String RutPaciente = getIntent().getStringExtra("RutPaciente");
        // Obtener solo los numeros del rut, para la consulta a la BD
        String RutTeaN = obtenerSoloNumerosRut(RutPaciente);
        databaseReference = FirebaseDatabase.getInstance().getReference("PersonaTEA");
        // Obtener el nombre del cesfam
        databaseReference.child(RutTeaN).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String gradoTEA = dataSnapshot.child("GradoTEA").getValue(String.class);
                    txtGrado.setText(gradoTEA);
                    String nombre = dataSnapshot.child("Nombre").getValue(String.class);
                    txtNombreOut.setText(nombre);

                }
                else {
                    Toast.makeText(Activity4.this, "Rut Incorrecto", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Activity4.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public String obtenerSoloNumerosRut(String rutConFormato) {
        // Elimina caracteres no numéricos
        return rutConFormato.replaceAll("[^0-9]", "");
    }
}