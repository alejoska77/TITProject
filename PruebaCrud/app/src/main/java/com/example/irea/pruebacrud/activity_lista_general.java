package com.example.irea.pruebacrud;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.irea.pruebacrud.model.Persona;
import com.example.irea.pruebacrud.model.Preguntas;
import com.example.irea.pruebacrud.model.Resultados;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class activity_lista_general extends AppCompatActivity {

    private List<Preguntas> listresultper = new ArrayList<Preguntas>();
    ArrayAdapter<Preguntas> arrayAdapteresultoer;

    private List<Persona> listresult = new ArrayList<Persona>();
    ArrayAdapter<Persona> arrayAdapteresult;

    private List<Resultados> listresultados = new ArrayList<Resultados>();
    ArrayAdapter<Resultados> arrayAdapteresultados;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ListView listV_resul;
    ListView listV_resul2;

    Resultados rolSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_detallada);

        listV_resul = findViewById(R.id.lv_datosResult);
        listV_resul2 = findViewById(R.id.lv_datosResult2);

        inicializarFirebase();
        listarDatosResult();

        listV_resul.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rolSelected=(Resultados) parent.getItemAtPosition(position);
                databaseReference.child("Test").child(rolSelected.getId()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        listresultper.clear();
                        for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                            Preguntas s= objSnaptshot.getValue(Preguntas.class);
                            listresultper.add(s);
                            arrayAdapteresultoer =new ArrayAdapter<Preguntas>(activity_lista_general.this, android.R.layout.simple_list_item_1, listresultper);
                            listV_resul2.setAdapter(arrayAdapteresultoer);




                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void listarDatosdestalle() {

        databaseReference.child("Test").child(rolSelected.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listresultper.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Preguntas s= objSnaptshot.getValue(Preguntas.class);
                    listresultper.add(s);
                    arrayAdapteresultoer =new ArrayAdapter<Preguntas>(activity_lista_general.this, android.R.layout.simple_list_item_2, listresultper);
                    listV_resul2.setAdapter(arrayAdapteresultoer);




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void listarDatosResult() {

        databaseReference.child("Resultados").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listresultados.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Resultados r= objSnaptshot.getValue(Resultados.class);
                    listresultados.add(r);
                    arrayAdapteresultados= new ArrayAdapter<Resultados>(activity_lista_general.this, android.R.layout.simple_list_item_1, listresultados);
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
