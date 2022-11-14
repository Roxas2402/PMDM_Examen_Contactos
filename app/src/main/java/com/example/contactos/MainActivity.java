package com.example.contactos;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.contactos.adapters.ContactosModelAdapters;
import com.example.contactos.constants.Constants;
import com.example.contactos.models.ContactoModel;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;


import com.example.contactos.databinding.ActivityMainBinding;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ContactosModelAdapters adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ContactoModel> contactoModelsList;
    private ActivityResultLauncher<Intent> launcherAddContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        contactoModelsList = new ArrayList<>();


        adapter = new ContactosModelAdapters(contactoModelsList, R.layout.contacto_view_holder, this);
        int columnas = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 2 : 1;
        layoutManager = new GridLayoutManager(this, columnas);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);
        binding.contentMain.contenedor.setAdapter(adapter);
        inicializaLaunchers();


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherAddContacto.launch(new Intent(MainActivity.this, AddContactoActivity.class));
            }
        });
    }

    private void inicializaLaunchers() {
        launcherAddContacto = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null && result.getData().getExtras() != null) {
                        ContactoModel c = (ContactoModel) result.getData().getExtras().getSerializable("CONT");
                        contactoModelsList.add(c);
                    }
                }
            }
        });
    }
}