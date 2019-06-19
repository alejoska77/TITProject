package com.example.irea.pruebacrud;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.irea.pruebacrud.model.Persona;
import com.example.irea.pruebacrud.model.Preguntas;
import com.example.irea.pruebacrud.model.Resultados;
import com.example.irea.pruebacrud.model.Tratamiento;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Activity_Tratamiento extends AppCompatActivity {



    private List<Persona> listresult = new ArrayList<Persona>();
    ArrayAdapter<Persona> arrayAdapteresult;

    private List<Tratamiento> listresultados = new ArrayList<Tratamiento>();
    ArrayAdapter<Tratamiento> arrayAdapteresultados;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ListView listV_resul;


    Resultados rolSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__tratamiento);

        listV_resul = findViewById(R.id.lv_datosTratamiento);


        inicializarFirebase();
        listarDatosResult();


    }

    private void listarDatosResult() {

        databaseReference.child("Tratamiento").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listresultados.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Tratamiento s= objSnaptshot.getValue(Tratamiento.class);
                    listresultados.add(s);
                    arrayAdapteresultados= new ArrayAdapter<Tratamiento>(Activity_Tratamiento.this, android.R.layout.simple_list_item_1, listresultados);
                    listV_resul.setAdapter(arrayAdapteresultados);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        // firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

}
