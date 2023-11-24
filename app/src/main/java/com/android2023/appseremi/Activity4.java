package com.android2023.appseremi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Activity4 extends AppCompatActivity {

    TextView txtRutTeaOut, txtNombreOut, txtGrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        txtRutTeaOut = findViewById(R.id.txtRutTeaOut);
        txtNombreOut = findViewById(R.id.txtNombreOut);
        txtGrado = findViewById(R.id.txtGrado);

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