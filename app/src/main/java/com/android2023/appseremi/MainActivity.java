package com.android2023.appseremi;

import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnEduc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Boton Educacion.
        btnEduc = findViewById(R.id.btnCrisis);
        btnEduc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,Activity2.class));

                // Verififcar si el dispositivo tiene contraseña. Si tiene solicitarla
                /* KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
                if (keyguardManager != null) {
                    if (keyguardManager.isKeyguardSecure()) {
                        Intent intent = keyguardManager.createConfirmDeviceCredentialIntent(null,null);
                        if (intent != null) {
                            startActivityForResult(intent, DEVICE_CREDENTIAL);
                        }
                    } else {

                        // El dispositivo no tiene un método de bloqueo seguro (PIN, patrón, contraseña).
                        Toast.makeText(MainActivity.this, "El dispositivo no tiene un método de bloqueo seguro", Toast.LENGTH_SHORT).show();

                    }
                } */
            }
        });


    }

    // Evento que recibe el resultado del pin. Si es correcto pasa a la siguiente Activity.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DEVICE_CREDENTIAL) {
            if (resultCode == RESULT_OK) {
                startActivity(new Intent(MainActivity.this,Activity2.class));
            } else {
                // La autenticación falló o el usuario canceló la operación.
                Toast.makeText(this, "Fallo la autenticacion", Toast.LENGTH_SHORT).show();
            }
        }
    }
}