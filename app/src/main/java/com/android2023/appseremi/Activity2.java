package com.android2023.appseremi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    EditText rutTeaIn, rutTutorIn;
    Button buscar;
    TextView txtinicio, txtNtea, txtNtutor;
    ImageView incrementa;
    int Contador = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        // Titulo Aplicacion
        txtinicio = findViewById(R.id.txtinicio);
        txtNtutor = findViewById(R.id.txtNTutor);
        txtNtea = findViewById(R.id.txtNTea);
        // Incrementar el tamaño de la letra
        incrementa = findViewById(R.id.incrementa);
        incrementa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contador++;
                txtinicio.setTextSize(20);
                buscar.setTextSize(24);
                txtNtea.setTextSize(22);
                txtNtutor.setTextSize(22);

                if(Contador == 2){
                    txtinicio.setTextSize(16);
                    buscar.setTextSize(16);
                    txtNtea.setTextSize(16);
                    txtNtutor.setTextSize(16);
                    Contador = 0;
                }
            }
        });
        // Boton buscar.
        buscar   = findViewById(R.id.btnBuscar);
        // Capturar los rut.
        rutTeaIn   = findViewById(R.id.txtRutTeaIn);
        rutTutorIn = findViewById(R.id.txtRutTutorIn);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Covertir a String.
                String RutPaciente = rutTeaIn.getText().toString();
                String RutTutor = rutTutorIn.getText().toString();
                // Validar si los campos de texto estan vacios.
                if(RutPaciente.isEmpty() || RutTutor.isEmpty()){
                    Toast.makeText(Activity2.this, "Complete los campos", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getApplicationContext(), Activity3.class);
                    intent.putExtra("RutPaciente", RutPaciente);
                    intent.putExtra("RutTutor", RutTutor);
                    startActivity(intent);
                }

            }
        });
    }

}