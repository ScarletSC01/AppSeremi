package com.android2023.appseremi;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
// Esta clase permite obtener el rut en formato chileno
public class RutTextWatcher implements TextWatcher {

    private EditText editText;

    public RutTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        String originalText = editable.toString();
        String formattedText = formatRut(originalText);

        editText.removeTextChangedListener(this);
        editText.setText(formattedText);
        editText.setSelection(formattedText.length());
        editText.addTextChangedListener(this);
    }

    private String formatRut(String input) {
        // Eliminar caracteres no numéricos
        String cleanRut = input.replaceAll("[^0-9]", "");

        // El RUT tenga al menos un dígito
        if (cleanRut.length() == 0) {
            return "";
        }

        // Formatear el RUT: XX.XXX.XXX-Y
        StringBuilder formattedRut = new StringBuilder();
        formattedRut.append(cleanRut.substring(0, Math.min(2, cleanRut.length())));

        if (cleanRut.length() > 2) {
            formattedRut.append(".").append(cleanRut.substring(2, Math.min(5, cleanRut.length())));
        }
        if (cleanRut.length() > 5) {
            formattedRut.append(".").append(cleanRut.substring(5, Math.min(8, cleanRut.length())));
        }
        if (cleanRut.length() > 8) {
            formattedRut.append("-").append(cleanRut.substring(8, Math.min(9, cleanRut.length())));
        }

        return formattedRut.toString();

    }
}

