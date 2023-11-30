package com.android2023.appseremi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activity3 extends AppCompatActivity {
    TextView txtRutTea, txtRutTutor, txtNombreCen;
    ImageView incrementa;
    int Contador = 0;
    DatabaseReference databaseReference;

    ImageView ubicacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        txtRutTea    = findViewById(R.id.txtRutTeaOut);
        txtRutTutor  = findViewById(R.id.txtNombreOut);
        txtNombreCen = findViewById(R.id.txtNomCentro);
        ubicacion    = findViewById(R.id.imgMap);


        // Recibir los rut desde la activity n°2.
        String RutPaciente = getIntent().getStringExtra("RutPaciente");
        String RutTutor = getIntent().getStringExtra("RutTutor");

        txtRutTea.setText(RutPaciente);
        txtRutTutor.setText(RutTutor);


        txtRutTea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Activity4.class);
                intent.putExtra("RutPaciente", RutPaciente);
                startActivity(intent);
            }
        });

        // Incrementar el tamaño de la letra
        incrementa = findViewById(R.id.incrementa);
        incrementa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contador++;
                txtRutTea.setTextSize(36);
                txtRutTutor.setTextSize(36);
                txtNombreCen.setTextSize(32);

                if(Contador == 2){
                    txtRutTea.setTextSize(24);
                    txtRutTutor.setTextSize(24);
                    txtNombreCen.setTextSize(24);
                    Contador = 0;
                }
            }
        });

        // Metodo
        CesfamPorRut();
    }

    public void CesfamPorRut(){
        String RutPaciente = getIntent().getStringExtra("RutPaciente");
        // Obtener solo los numeros del rut, para la consulta a la BD
        String RutTeaN = obtenerSoloNumerosRut(RutPaciente);
        databaseReference = FirebaseDatabase.getInstance().getReference("PersonaTEA");
        // Obtener el nombre del cesfam
        databaseReference.child(RutTeaN).orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        // Obtener el nombre identificador, en este caso el cesfam
                        String cesfamKey = childSnapshot.getKey();
                        txtNombreCen.setText(cesfamKey);
                        // Ubicacion de acuerdo al Cesfam
                        ubicacion.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Acceder a la latitud y longitud desde la BD
                                Double latitudCesfam = childSnapshot.child("Latitud").getValue(Double.class);
                                Double longitudCesfam = childSnapshot.child("Longitud").getValue(Double.class);

                                // Pasar Los datos a la siguiente actividad
                                Intent intent = new Intent(Activity3.this,Activity3Map.class);
                                intent.putExtra("Nombrecesfam",cesfamKey);
                                intent.putExtra("Latitud", latitudCesfam);
                                intent.putExtra("Longitud",longitudCesfam);
                                startActivity(intent);
                            }
                        });
                        break;
                    }
                } else {
                    Toast.makeText(Activity3.this, "Rut Incorrecto", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Activity3.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public String obtenerSoloNumerosRut(String rutConFormato) {
        // Elimina caracteres no numéricos
        return rutConFormato.replaceAll("[^0-9]", "");
    }

}