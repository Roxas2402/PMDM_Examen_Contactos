package com.example.contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.contactos.constants.Constants;
import com.example.contactos.databinding.ActivityAddContactoBinding;
import com.example.contactos.models.ContactoModel;

public class AddContactoActivity extends AppCompatActivity {

    private ActivityAddContactoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddContactoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCrearAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = binding.txtNombreAdd.getText().toString();
                String ciclo = binding.txtCicloAdd.getText().toString();
                String numeroS = binding.txtTelefonoAdd.getText().toString();

                if (!nombre.isEmpty() && !ciclo.isEmpty() && !numeroS.isEmpty()) {
                    ContactoModel c = new ContactoModel(nombre, ciclo, Integer.parseInt(numeroS));
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("CONT", c);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(AddContactoActivity.this, Constants.ERROR, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}