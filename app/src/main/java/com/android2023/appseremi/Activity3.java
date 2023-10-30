package com.android2023.appseremi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Activity3 extends AppCompatActivity {
    EditText rutTeaOut, rutTutorOut;
    TextView txtRutTea, txtRutTutor, txtNombreCen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        txtRutTea   = findViewById(R.id.txtRutTeaOut);
        txtRutTutor = findViewById(R.id.txtNombreOut);
        txtNombreCen = findViewById(R.id.txtNomCentro);

        // Recibir los rut desde la activity n°2.
        String RutPaciente = getIntent().getStringExtra("RutPaciente");
        String RutTutor = getIntent().getStringExtra("RutTutor");

        txtRutTea.setText(RutPaciente);
        txtRutTutor.setText(RutTutor);

        // Metodo Consultar Cesfam por rut
        ConsultarCesfam();

        txtRutTea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Activity4.class);
                intent.putExtra("RutPaciente", RutPaciente);
                startActivity(intent);
            }
        });

    }

    public void ConsultarCesfam() {
        try {
            // Rut a String
            String rutPersona = txtRutTea.getText().toString();
            String rutTutor = txtRutTutor.getText().toString();
            // Instancia de la conexion
            DataBaseTEA dataBaseTEA = new DataBaseTEA();
            // Crear la conexion
            Statement stm = dataBaseTEA.conexionSQL().createStatement();
            // preparar sentencia SQL
            ResultSet rs = stm.executeQuery("SELECT CEFAM.Nombre_del_CEFAM " +
                    "FROM Personas " +
                    "INNER JOIN CEFAM ON Personas.CEFAM_ID = CEFAM.ID " +
                    "WHERE Personas.Rut = '" + rutPersona + "' " +
                    "AND Personas.Tutor_Rut = '" + rutTutor + "'");
            if (rs.next()) {
                // Mostrar Resultado en pantalla
                txtNombreCen.setText("Ficha Clinica " + rs.getString(1));
            }
        } catch (SQLException e) {
            Toast.makeText(this, "Fallo", Toast.LENGTH_SHORT).show();
        }
    }

}